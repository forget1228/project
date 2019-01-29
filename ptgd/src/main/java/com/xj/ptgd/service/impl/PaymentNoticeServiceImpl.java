package com.xj.ptgd.service.impl;

import com.xj.ptgd.common.exception.ResultException;
import com.xj.ptgd.common.result.ResultEnum;
import com.xj.ptgd.common.result.ResultUtil;
import com.xj.ptgd.common.util.CommonMethods;
import com.xj.ptgd.dao.ObjectMaxSnDao;
import com.xj.ptgd.dao.PaymentNoticeDao;
import com.xj.ptgd.entity.body.BodyEntity;
import com.xj.ptgd.entity.body.ObjectMaxSn;
import com.xj.ptgd.entity.body.PaymentNotice;
import com.xj.ptgd.entity.in.PaymentNoticeXMLIn;
import com.xj.ptgd.entity.out.BodyEntityXMLOut;
import com.xj.ptgd.service.PaymentNoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.xj.ptgd.common.config.YmlConfig.*;

/**
 * G374 推送还款信息
 * 接口功能概述	供应链通过此接口推送借据及还款信息
 * 调用说明	融资客户通过对公网银发起还款，银行成功受理后，供应链通过此接口将借据及还款信息推送给中证。
 * @date 2018/8/2
 */
@Service
public class PaymentNoticeServiceImpl implements PaymentNoticeService {
    @Resource
    PaymentNoticeDao paymentNoticeDao;

    @Resource
    ObjectMaxSnDao objectMaxSnDao;

    @Transactional
    @Override
    public String findPaymentNotice(PaymentNoticeXMLIn xmlIn) {
        PaymentNotice body = xmlIn.getBody();
        String condition = body.getLOAN_NO(); //合同表借据号
        Map<String,Object> map =  paymentNoticeDao.findBusinessContract(condition); //返回合同信息
        if(map == null){
            throw new  ResultException(ResultEnum.http_status_data_null,"无合同信息");
        }
        Map<String,Object> mapL =  paymentNoticeDao.findLoan(map.get("serialno")+""); //返回借据信息
        String loanId = "";
        String paymentLogId = "";

        /* 借据表 */
        map.put("LOAN_BALANCE",body.getLOAN_BALANCE()); //借据余额
        map.put("BusinessNo",map.get("ORDERSN")); //渠道订单编号
        map.put("ProjectNo", PROJECT_NO);  //项目编号
        map.put("CooperationNo", COOPERATION_NO);  //合作机构编号
        map.put("JIEJU_END_DATE",CommonMethods.changeDateFmt(body.getJIEJU_END_DATE(),"yyyyMMdd","yyyy-MM-dd"));  //借据到期日

        String hqFlag = body.getHQ_FLAG();
        if("1".equals(hqFlag)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = sdf.format(new Date());
            map.put("FINISHDATE_Time",dateString);  //结清日期
        }else {
            map.put("FINISHDATE_Time",null);  //结清日期
        }
        map.put("HQ_FLAG",hqFlag);  //结清日期

        if (mapL != null){
            paymentNoticeDao.UpDateLoan(map);
        }else {
            loanId = getIdPrimary("ACCT_LOAN","SERIALNO","yyyyMMdd");
            map.put("loanId",loanId); //借据表主键
            paymentNoticeDao.insertLoan(map);
        }
        /* 交易表 */
        map.put("CHANNEL_CODE",body.getCHANNEL_CODE()); //接入机构号
        map.put("REPAY_NO",body.getREPAY_NO()); //还款流水号
        map.put("REPAY_AMOUNT",body.getREPAY_AMOUNT()); //还款本金金额
        map.put("INTEREST_MONEY",body.getREFUND_INTEREST()); //还息金额

        String sDateStr=body.getREPAY_DATETIME();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateSrc = "";
        try {
            dateSrc = sdf2.format(sdf.parse(sDateStr));
        } catch (ParseException e) {
            //做转化失败的操作
        }
        map.put("REPAY_DATETIME",dateSrc); //还款时间
        map.put("actualpaydate",dateSrc.substring(0,10)); //还款时间
        paymentLogId = getIdPrimary("ACCT_PAYMENT_LOG","SERIALNO","yyyyMMdd");
        map.put("paymentLogId",paymentLogId); //交易表主键
        paymentNoticeDao.insertPaymentLog(map);

        BodyEntity bodyEntity = new BodyEntity();
        bodyEntity.setRESULT_FLAG("1");
        bodyEntity.setMESSAGE("");
        bodyEntity.setATTRIBUTE6("");
        bodyEntity.setATTRIBUTE7("");
        BodyEntityXMLOut out = new BodyEntityXMLOut();   // 出参
        out.setBody(bodyEntity);
        out.setHead(xmlIn.getHead());
        return ResultUtil.getResult(out,BodyEntityXMLOut.class);
    }


    private String getIdPrimary(String tableName,String columnName,String dateFmt) {
        ObjectMaxSn entity = objectMaxSnDao.findObjectMax(tableName,columnName,dateFmt);
        if (entity == null){
            throw new ResultException(ResultEnum.http_status_data_null,"流水号数据暂无，请确认后重新请求");
        }
        // 生成序列号
        CommonMethods commonMethods = new CommonMethods();
        String id = commonMethods.idPrimaryNextNumber(entity.getMaxSerialNo(),entity.getNoFmt(),entity.getDateFmt());
        if (id == null) throw new ResultException(ResultEnum.http_status_internal_server_error,ResultEnum.http_status_internal_server_error.getMsg());
        System.out.println(id);
        // 修改最大值
        objectMaxSnDao.updateObjectMaxSnForMaxSerialNo(id,entity.getTableName(),entity.getColumnName(),entity.getDateFmt());
        return id;
    }
}
