package com.xj.ptgd.service.impl;

import com.xj.ptgd.common.exception.ResultException;
import com.xj.ptgd.common.result.ResultEnum;
import com.xj.ptgd.common.result.ResultUtil;
import com.xj.ptgd.common.util.CommonMethods;
import com.xj.ptgd.dao.*;
import com.xj.ptgd.entity.body.BodyEntityMSG;
import com.xj.ptgd.entity.body.LoanNotice;
import com.xj.ptgd.entity.body.ObjectMaxSn;
import com.xj.ptgd.entity.in.LoanNoticeXMLIn;
import com.xj.ptgd.entity.out.BodyEntityMSGXMLOut;
import com.xj.ptgd.service.LoanNoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.xj.ptgd.common.config.YmlConfig.*;

/**
 * G373 放款通知推送
 * 接口功能概述	供应链将放款通知信息推送至中证
 * 调用说明	供应链发起放款申请后，网贷易放款交易将放款信息返回给供应链，供应链更新订单状态并推送通知
 * @date 2018/8/2
 */
@Service
public class LoanNoticeServiceImpl implements LoanNoticeService {
    @Resource
    LoanNoticeDao loanNoticeDao;

    @Resource
    ObjectMaxSnDao objectMaxSnDao;

    @Resource
    IntendedDao intendedDao;

    @Resource
    OrderDao orderDao;

    @Transactional
    @Override
    public String loanNotice(LoanNoticeXMLIn in) {
        String type = "";
        if (in.getBody().getCUSTOMER_CODE().length()>9){ type = "Ent04"; }else type = "Ent01";
        LoanNotice body = in.getBody();

        Map<String,Object> customerMap = intendedDao.findCustomerInfo(type,body.getCUSTOMER_CODE());
        if (customerMap == null ) throw new ResultException(ResultEnum.http_status_data_null,"客户社会信用代码不存在，请检查后重新请求");

        Map<String,Object> mapBcIn = new HashMap<>();
        mapBcIn.put("CHANNEL_CODE",body.getCHANNEL_CODE()); // 接入机构号
        mapBcIn.put("CUSTOMER_CODE",body.getCUSTOMER_CODE()); // 社会统一信用代码
        mapBcIn.put("CUSTOMER_NAME",body.getCUSTOMER_NAME()); // 企业名称
        mapBcIn.put("OWNER_NAME",customerMap.get("CUSTOMERNAME")); // 企业主姓名
        mapBcIn.put("OWNER_IDCARD",customerMap.get("CERTID")); // 企业主身份证号
        mapBcIn.put("OWNER_PHONENO",customerMap.get("phone")); // 企业主手机号
        mapBcIn.put("EXECUTE_RATE",body.getEXECUTE_RATE()); // 执行利率
        mapBcIn.put("ORDER_NO",body.getORDER_NO()); // 订单编号
        mapBcIn.put("LOUS_NO",body.getLOUS_NO()); // 借据编号
        mapBcIn.put("LOAN_MONEY",body.getLOAN_MONEY()); // 放款金额
        mapBcIn.put("LOAN_START_DATE",CommonMethods.changeDateFmt(body.getLOAN_START_DATE(),"yyyyMMdd","yyyy-MM-dd")); // 放款起始日期
        mapBcIn.put("LOAN_END_DATE",CommonMethods.changeDateFmt(body.getLOAN_END_DATE(),"yyyyMMdd","yyyy-MM-dd")); // 放款到期日期

        mapBcIn.put("customerid",customerMap.get("CUSTOMERID")); //

        mapBcIn.put("businesstype",BUSINESS_TYPE); // 增信产品号
        mapBcIn.put("paytimes",1); // 还款期数  int
        mapBcIn.put("purpose","支付订单货款"); // 用途
        mapBcIn.put("IsBookIn","1"); // 是否签约1否2是
        mapBcIn.put("RETURNFREQUENCY","随借随还"); // 还款频率
        mapBcIn.put("PROJECTCODE", PROJECT_NO); // 项目编号
        mapBcIn.put("SERVORGNO",COOPERATION_NO); // 贷款服务机构编号
        mapBcIn.put("CURRENCY","01"); // 币种
        mapBcIn.put("ReCustomerID",customerMap.get("CUSTOMERID")); // 所属企业编号

        Map<String,Object> orderMap = orderDao.findGdOrderInfo(body.getORDER_NO(),body.getCUSTOMER_CODE());
        if (orderMap == null){
            throw new ResultException(ResultEnum.http_status_data_null,"该请求参数查询无此订单数据，请确认后重新请求");
        }
        orderDao.updateGdStatus(body.getCUSTOMER_CODE(),body.getORDER_NO(),body.getORDER_STATUS(),body.getREJECT_REASON(),body.getCHANNEL_CODE());

        if ("4".equals(body.getORDER_STATUS())){
            Integer startTimeY = Integer.parseInt(body.getLOAN_START_DATE().substring(0,4));
            Integer startTimeM = Integer.parseInt(body.getLOAN_START_DATE().substring(4,6));
            Integer endTimeY = Integer.parseInt(body.getLOAN_END_DATE().substring(0,4));
            Integer endTimeM = Integer.parseInt(body.getLOAN_END_DATE().substring(4,6));
            String termmonth = ((endTimeY-startTimeY)*12 + (endTimeM-startTimeM)+1)+""; // 期限
            mapBcIn.put("termmonth",termmonth); // 期限
            if (loanNoticeDao.findBusinessContractOrderId(mapBcIn) != null){
                throw new ResultException(ResultEnum.http_status_data_have,"该订单编号已生成合同信息，请检查后重新请求");
            }

            if (loanNoticeDao.findBusinessContractLoan(mapBcIn) != null){
                throw new ResultException(ResultEnum.http_status_data_have,"该借据编号已生成合同信息，请检查后重新请求");
            }

            String serialno =  getIdPrimary("BUSINESS_CONTRACT","SERIALNO","'BC'yyyyMMdd");
            mapBcIn.put("serialno",serialno); //合同信息表主键
            loanNoticeDao.insertBusinessContract(mapBcIn);

            // 生成第一条   借据  贷款-贷款信息
            mapBcIn.put("loanId",getIdPrimary("ACCT_LOAN","SERIALNO","yyyyMMdd")); //借据表主键
            loanNoticeDao.insertAcctLoan(mapBcIn);
        }

        BodyEntityMSG bodyEntity = new BodyEntityMSG();
        bodyEntity.setRESULT_FLAG("1");
        bodyEntity.setMSG("");
        bodyEntity.setATTRIBUTE6("");
        bodyEntity.setATTRIBUTE7("");
        BodyEntityMSGXMLOut out = new BodyEntityMSGXMLOut();   // 合集出参
        out.setBody(bodyEntity);
        out.setHead(in.getHead());
        return ResultUtil.getResult(out,BodyEntityMSGXMLOut.class);
    }

    private String getIdPrimary(String tableName, String columnName, String dateFmt) {
        ObjectMaxSn entity = objectMaxSnDao.findObjectMax(tableName,columnName,dateFmt);
        //System.out.println(entity);
        if (entity == null){
            throw new ResultException(ResultEnum.http_status_data_null,"流水号数据暂无，请确认后重新请求");
        }
        String id = CommonMethods.idPrimaryNextNumber(entity.getMaxSerialNo(),entity.getNoFmt(),entity.getDateFmt());   // 生成序列号
        if (id == null) {
            throw new ResultException(ResultEnum.http_status_internal_server_error,ResultEnum.http_status_internal_server_error.getMsg());
        }
        // 修改最大值
        objectMaxSnDao.updateObjectMaxSnForMaxSerialNo(id,entity.getTableName(),entity.getColumnName(),entity.getDateFmt());
        return id;
    }
}
