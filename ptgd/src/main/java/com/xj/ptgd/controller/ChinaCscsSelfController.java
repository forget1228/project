package com.xj.ptgd.controller;

import com.xj.ptgd.common.exception.ResultException;
import com.xj.ptgd.common.httpXml.HttpsUtils;
import com.xj.ptgd.common.result.ResultEnum;
import com.xj.ptgd.common.result.ResultUtil;
import com.xj.ptgd.common.util.JaxbUtil;
import com.xj.ptgd.common.util.MacUtil;
import com.xj.ptgd.entity.base.XMLHeadDto;
import com.xj.ptgd.entity.body.Error;
import com.xj.ptgd.entity.out.ErrorXMLOut;
import com.xj.ptgd.service.*;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.xj.ptgd.common.config.YmlConfig.TRANSACTION_URL;
import static com.xj.ptgd.common.util.MacUtil.*;

/**
 *  服务方-中证
 *  @author cp
 *  @date 2018/8/2
 */
@RestController
public class ChinaCscsSelfController {
    // 日志输出
    private Logger logger = LoggerFactory.getLogger(ChinaCscsSelfController.class);
    @Autowired
    LoanFinancingService loanFinancingService;
    @Autowired
    OrderCancelService orderCancelService;
    @Autowired
    PaymentNoticeService paymentNoticeService;
    @Autowired
    IntendedService intendedService;
    @Autowired
    LoanNoticeService loanNoticeService;
    @Autowired
    DealerDataService dealerDataService;
    @Autowired
    PushContractService pushContractService;
    @Autowired
    NoticeContractService noticeContractService;

                                                                            //produces = { "application/xml;charset=ISO-8859-1" }
    @RequestMapping(value = "/tochinacscs",method = { RequestMethod.POST },produces = { "application/xml;charset=GB18030" })
    public String tochinacscs(@RequestBody String req){
        String ret = "";
        req = req.trim();

        JSONObject xmlIn = null;
        XMLHeadDto outHead = new XMLHeadDto();
        try {
            String xml = subXMLForMAC(req);
            // 请求XML
            if ("".equals(xml) ){
                throw new ResultException(ResultEnum.http_status_request_null,ResultEnum.http_status_request_null.getMsg());
            }
            xmlIn = JaxbUtil.getJSONFromXml(xml);
            logger.info("请求报文："+req);
            JSONObject head = xmlIn.getJSONObject("Head");
            outHead.setChnlNo(head.getString("ChnlNo"));
            outHead.setFTranCode(head.getString("FTranCode"));
            outHead.setTrmSeqNum(head.getString("TrmSeqNum"));
            outHead.setTranDateTime(head.getString("TranDateTime"));
            outHead.setInstID(head.getString("InstID"));
            outHead.setErrCode("");
            String FTranCode = head.getString("FTranCode");
            if ("[]".equals(FTranCode) || "".equals(FTranCode)) {
                throw new ResultException(ResultEnum.http_status_request_null,ResultEnum.http_status_request_null.getMsg());
            }
            if (!checkMAC(req)){
                throw new ResultException(ResultEnum.http_status_run_mac,"MAC校验不通过!!");
            }
            ret = InterfaceSelection(xml,FTranCode);
        }catch (Exception ex){
            logger.info("Controller--请求信息错误");
            ex.printStackTrace();
            ErrorXMLOut errorXMLOut = new ErrorXMLOut();
            Error error = new Error();
            if (ex instanceof NullPointerException){  /* 请求参数异常 */
                error.setErrorCode(ResultEnum.http_status_request_null.getCode());
                error.setErrorInfo(ResultEnum.http_status_request_null.getMsg());
            }else if(ex instanceof JSONException){ /* 请求参数异常 */
                error.setErrorCode(ResultEnum.http_status_request_null.getCode());
                error.setErrorInfo(ResultEnum.http_status_request_null.getMsg());
            }else if(ex instanceof ResultException){ /* 自定义异常 */
                ResultException re = (ResultException) ex;
                error.setErrorCode(re.getCode());
                error.setErrorInfo(re.getMessage());
            }else{ /* 其他系统异常 */
                error.setErrorCode(ResultEnum.http_status_internal_server_error.getCode());
                error.setErrorInfo(ResultEnum.http_status_internal_server_error.getMsg());
            }
//            outHead.setErrCode(error.getErrorCode()+"");  //错误信息代码
            errorXMLOut.setHead(outHead);
            errorXMLOut.setBody(error);
            return  MacUtil.addLen(MacUtil.addMac(ResultUtil.getResult(errorXMLOut,ErrorXMLOut.class)));
        }
        String retString = MacUtil.addLen(MacUtil.addMac(ret));
        System.out.println("********************** start *********************************************");
        System.out.println("应答时间："+new Date().toString()+"\n接口返回报文信息："+retString);
        System.out.println("************************  end ******************************************");
        return retString;
    }

    private ChinaCscsSelfFirstController chinaCscsSelfFirstController = new ChinaCscsSelfFirstController();
    private ChinaCscsSelfSecondController cscsSelfSecondController = new ChinaCscsSelfSecondController();
    private ChinaCscsSelfThreeController chinaCscsSelfThreeController = new ChinaCscsSelfThreeController();

    private String InterfaceSelection(String xml,String FTranCode){
        String ret = "";
        if("G371".equals(FTranCode)){
            ret = cscsSelfSecondController.findDealer(xml,dealerDataService);
        }else if("G348".equals(FTranCode)){
            ret = chinaCscsSelfFirstController.intention(xml,intendedService);
        }else if("G373".equals(FTranCode)){
            ret = cscsSelfSecondController.loanNotice(xml,loanNoticeService);
        }else if("G374".equals(FTranCode)){
            ret = cscsSelfSecondController.findPaymentNotice(xml,paymentNoticeService);
        }else if("G353".equals(FTranCode)){
            ret = chinaCscsSelfFirstController.loanFinancing(xml,loanFinancingService);
        }else if("G362".equals(FTranCode)){
            ret = chinaCscsSelfFirstController.findOrderCancel(xml,orderCancelService);
        }else if("G390".equals(FTranCode)){
            ret = chinaCscsSelfThreeController.pushContract(xml,pushContractService);
        }else if("G392".equals(FTranCode)){
            ret = chinaCscsSelfThreeController.noticeContract(xml,noticeContractService);
        }else{
            throw new ResultException(ResultEnum.http_status_bad_request,ResultEnum.http_status_bad_request.getMsg());
        }
        return ret;
    }

    private Send9000 send9000 = new Send9000();
    @RequestMapping(value = "/getSendTransaction",method = { RequestMethod.POST },produces = { "application/xml" })
    public String sendReq(@RequestBody String req){
        return send9000.StartXML(req);
    }

   @RequestMapping(value = "/cvisa",method = { RequestMethod.POST },produces = { "application/xml" })
    public String sendZZ(@RequestBody String req){
//        String url = "https://111.205.51.104:3031";
//        String xml ="<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><In><Head><ChnlNo>303</ChnlNo><FTranCode>G351</FTranCode><InstID>03030005</InstID><TrmSeqNum>914864303</TrmSeqNum><TranDateTime>20180930172105</TranDateTime><ErrCode></ErrCode></Head><Body><CHANNEL_CODE>03030005</CHANNEL_CODE><AMOUNT>1</AMOUNT><Frame><CUSTOMER_CODE>91340100085225275P</CUSTOMER_CODE><CUST_NO>1234567890</CUST_NO><CUSTOMER_NAME>厦门县卡尔文邮电办</CUSTOMER_NAME><PAUSE_REASON>拒绝</PAUSE_REASON><ATTRIBUTE1></ATTRIBUTE1><ATTRIBUTE2></ATTRIBUTE2><ATTRIBUTE3></ATTRIBUTE3></Frame></Body></In>";
        String xml = req;
        xml = addLen(xml+toMAC(xml));
        System.out.println("请求报文:"+xml);
       String ret = HttpsUtils.sendByHttp(xml, TRANSACTION_URL);
        System.out.println("应答报文:"+ret);
        return ret;
    }
}