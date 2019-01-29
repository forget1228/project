package com.xj.ptgd.controller;

import com.xj.ptgd.common.exception.ResultException;
import com.xj.ptgd.common.result.ResultEnum;
import com.xj.ptgd.common.result.ResultUtil;
import com.xj.ptgd.common.util.CommonMethods;
import com.xj.ptgd.common.util.JaxbUtil;
import com.xj.ptgd.entity.body.BodyEntity;
import com.xj.ptgd.entity.body.BodyEntityMSG;
import com.xj.ptgd.entity.body.BodyOrderCancel;
import com.xj.ptgd.entity.in.*;
import com.xj.ptgd.entity.out.BodyEntityMSGXMLOut;
import com.xj.ptgd.entity.out.BodyEntityXMLOut;
import com.xj.ptgd.entity.out.BodyOrderCancelXMLOut;
import com.xj.ptgd.service.*;
import net.sf.json.JSONException;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.transaction.CannotCreateTransactionException;


/**
 *  服务方-中证
 *  @author cp
 *  @date 2018/8/2
 */
public class ChinaCscsSelfFirstController {
    // 日志输出
    private Logger logger = LoggerFactory.getLogger(ChinaCscsSelfFirstController.class);

    /**
     * G348 推送客户额度信息
     * 供应链手动将额度信息推送给中证，只在批复生效，额度启用后，由业务人员手工触发推送客户
     * @return
     */
    public String intention(String req,IntendedService intendedService){
        logger.info("Controller--G348推送客户额度信息");
        // 业务处理
        String str = "";
        IntentionXmlIn xmlIn = new IntentionXmlIn();
        try {
            JaxbUtil ju = new JaxbUtil(IntentionXmlIn.class);
            xmlIn = ju.fromXml(req);


            // 接收参数
            String channelCode = xmlIn.getBody().getCHANNEL_CODE();           // 接入机构号
            String customerCode = xmlIn.getBody().getCUSTOMER_CODE();         // 客户社会信用代码
            String customerName = xmlIn.getBody().getCUSTOMER_NAME();         // 客户名称
            String creditLineState = xmlIn.getBody().getCREDITLINE_STATE();   // 额度状态
            String totalLimit = xmlIn.getBody().getTOTAL_LIMIT();             // 总额度
            String availableLimit = xmlIn.getBody().getAVAILABLE_LIMIT();     // 可用额度
            String limitBeginDate = xmlIn.getBody().getLIMIT_BEGIN_DATE();    // 额度开始日期
            String limitEndDate =xmlIn.getBody().getLIMIT_END_DATE();         // 额度到期日
            // 入参检查
            // 入参必须项检查
            if (channelCode == null || "".equals(channelCode)){
                throw new ResultException(ResultEnum.http_status_null_message,"接入机构号不能为空，请确认后重新请求");
            } else xmlIn.getBody().setCHANNEL_CODE(CommonMethods.trimAllBlanks(channelCode));
            if (customerCode == null || "".equals(customerCode)){
                throw new ResultException(ResultEnum.http_status_null_message,"社会统一信用代码不能为空，请确认后重新请求");
            } else xmlIn.getBody().setCUSTOMER_CODE(CommonMethods.trimAllBlanks(customerCode));
            if (customerName == null || "".equals(customerName)){
                throw new ResultException(ResultEnum.http_status_null_message,"客户名称不能为空，请确认后重新请求");
            } else xmlIn.getBody().setCUSTOMER_NAME(CommonMethods.trimAllBlanks(customerName));
            if (creditLineState == null || "".equals(creditLineState)){
                throw new ResultException(ResultEnum.http_status_null_message,"额度状态不能为空，请确认后重新请求");
            } else creditLineState = CommonMethods.trimAllBlanks(creditLineState);
            if ("".equals(totalLimit) || totalLimit == null){
                throw new ResultException(ResultEnum.http_status_null_message,"总额度不能为空，请确认后重新请求");
            }else totalLimit = CommonMethods.trimAllBlanks(totalLimit);
            if ("".equals(availableLimit) || availableLimit == null){
                throw new ResultException(ResultEnum.http_status_null_message,"可用额度不能为空，请确认后重新请求");
            }else availableLimit = CommonMethods.trimAllBlanks(availableLimit);
            if (limitBeginDate == null || "".equals(limitBeginDate)){
                throw new ResultException(ResultEnum.http_status_null_message,"额度开始日期不能为空，请确认后重新请求");
            }else limitBeginDate = CommonMethods.trimAllBlanks(limitBeginDate);
            if (limitEndDate == null || "".equals(limitEndDate)){
                throw new ResultException(ResultEnum.http_status_null_message,"额度到期日不能为空，请确认后重新请求");
            }else limitEndDate = CommonMethods.trimAllBlanks(limitEndDate);
            // 入参类型检查
            double totalLimit1,availableLimit1 ;
            try {
                totalLimit1 = Double.parseDouble(totalLimit);
                xmlIn.getBody().setTOTAL_LIMIT(totalLimit);
            }catch(Exception ex){
                throw new ResultException(ResultEnum.http_status_not_number,"总额度格式错误，请输入数字类型");
            }
            try {
                availableLimit1 = Double.parseDouble(availableLimit);
                xmlIn.getBody().setAVAILABLE_LIMIT(availableLimit);
            }catch(Exception ex){
                throw new ResultException(ResultEnum.http_status_not_number,"可用额度格式错误，请输入数字类型");
            }
            if (!CommonMethods.checkIsDate(limitBeginDate)){
                throw new ResultException(ResultEnum.http_status_date_message,"额度开始日期格式错误，格式为 yyyyMMdd");
            }else xmlIn.getBody().setLIMIT_BEGIN_DATE(CommonMethods.changeDateFmt(limitBeginDate,"yyyyMMdd","yyyy-MM-dd HH:mm:ss"));
            if (!CommonMethods.checkIsDate(limitEndDate)){
                throw new ResultException(ResultEnum.http_status_date_message,"额度到期日格式错误，格式为 yyyyMMdd");
            }else xmlIn.getBody().setLIMIT_END_DATE(CommonMethods.changeDateFmt(limitEndDate,"yyyyMMdd","yyyy-MM-dd HH:mm:ss"));
            // 入参固定值判断
            if ("正常".equals(creditLineState) || "1".equals(creditLineState)){
                xmlIn.getBody().setCREDITLINE_STATE("1");
            }else if ("暂停".equals(creditLineState) || "2".equals(creditLineState)){
                xmlIn.getBody().setCREDITLINE_STATE("6");
            }else {
                throw new ResultException(ResultEnum.http_status_data_fix,"额度状态不符，请确认后重新请求");
            }
            // 入参长度检查
            if (channelCode.length() > 8)   throw new ResultException(ResultEnum.http_status_data_too_long,"接入机构号长度过长，请确认后重新请求");
            if (customerCode.length() > 30) throw new ResultException(ResultEnum.http_status_data_too_long,"社会统一信用代码长度过长，请确认后重新请求");
            if (customerName.length() < 2) throw new ResultException(ResultEnum.http_status_data_too_short,"客户名称长度过短，请确认后重新请求");
            if (customerName.length() > 120)    throw new ResultException(ResultEnum.http_status_data_too_long,"客户名称长度过长，请确认后重新请求");
            // 入参关联检查
            if (availableLimit1 > totalLimit1)  throw new ResultException(ResultEnum.http_status_data_compare_number,"可用额度不能大于总额度，请确认后重新请求");
            if (!CommonMethods.compareDate(limitBeginDate,limitEndDate))    throw new ResultException(ResultEnum.http_status_data_compare_date,"额度开始日期不能晚于额度到期日期，请确认后重新请求");
            // 入参特殊字符
            if (CommonMethods.checkMySqlReservedWords(channelCode))    throw new ResultException(ResultEnum.http_status_data_special,"接入机构号不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(customerCode))    throw new ResultException(ResultEnum.http_status_data_special,"社会统一信用代码不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(customerName))    throw new ResultException(ResultEnum.http_status_data_special,"客户名称不能有特殊字符，请确认后重新请求");
            logger.info("参数验证完毕，进行后续业务处理");
            str = intendedService.save(xmlIn);
            logger.info("--G348推送客户额度信息--END");
        }catch (Exception e){
            logger.info("--G348推送客户额度信息--ERROR");
            e.printStackTrace();
            BodyEntityXMLOut out = new BodyEntityXMLOut();   // 合集出参
            BodyEntity bodyEntity = new BodyEntity();   // 出参定义 Body
            bodyEntity.setRESULT_FLAG("0"); //失败
            if (e instanceof NullPointerException || e instanceof JSONException || e instanceof ClassCastException){  /* 请求参数异常 */
                bodyEntity.setMESSAGE(ResultEnum.http_status_request_null.getMsg());
            }else if(e instanceof ResultException){ /* 自定义异常 */
                bodyEntity.setMESSAGE(e.getMessage());
            }else if (e instanceof BadSqlGrammarException){/*  数据库语法错误 */
                bodyEntity.setMESSAGE(ResultEnum.http_status_sql_grammar_mistakes.getMsg());
            }else if (e instanceof MyBatisSystemException || e instanceof CannotCreateTransactionException){    /* 数据库连接异常*/
                bodyEntity.setMESSAGE(ResultEnum.http_status_sql_connection.getMsg());
            }else if (e instanceof DuplicateKeyException){    /* 数据库主键唯一异常*/
                bodyEntity.setMESSAGE(ResultEnum.http_status_sql_primary.getMsg());
            }else{ /* 其他系统异常 */
                bodyEntity.setMESSAGE(ResultEnum.http_status_internal_server_error.getMsg());
            }
            bodyEntity.setATTRIBUTE6("");
            bodyEntity.setATTRIBUTE7("");
            out.setHead(xmlIn.getHead());
            out.setBody(bodyEntity);
            // 转换成xml格式
            return ResultUtil.getResult(out,BodyEntityXMLOut.class);
        }

        return str;
    }

    /**
     *  G353 订单融资申请转发中证
     *  供应链订单融资信息转发至中证
     * @return
     */
    public String loanFinancing(String req,LoanFinancingService loanFinancingService){
        logger.info("Controller--G353订单融资申请转发中证");
        String str = "";
        // 请求XML
        LoanFinancingXMLIn xmlIn = new LoanFinancingXMLIn();
        try {
            JaxbUtil ju = new JaxbUtil(LoanFinancingXMLIn.class);
            xmlIn = ju.fromXml(req);

            // 接收参数
            String channelCode = xmlIn.getBody().getCHANNEL_CODE();
            String customerCode = xmlIn.getBody().getCUSTOMER_CODE();
            String customerName = xmlIn.getBody().getCUSTOMER_NAME();
            String orderNo = xmlIn.getBody().getORDER_NO();
            String orderAmount = xmlIn.getBody().getORDER_AMOUNT();
            String orderDate = xmlIn.getBody().getORDER_DATE();
            String selfPay = xmlIn.getBody().getSELF_PAY();
            String selfPayRate = xmlIn.getBody().getSELF_PAY_RATE();
            String branchCompany = xmlIn.getBody().getBRACH_COMPANY();
            String branchAccount = xmlIn.getBody().getBRACH_ACCOUNT();
            // 入参检查
            // 入参必须项检查
            if (channelCode == null || "".equals(channelCode)) {
                throw new ResultException(ResultEnum.http_status_null_message, "接入机构号不能为空，请确认后重新请求");
            } else xmlIn.getBody().setCHANNEL_CODE(channelCode = CommonMethods.trimAllBlanks(channelCode));
            if (customerCode == null || "".equals(customerCode)) {
                throw new ResultException(ResultEnum.http_status_null_message, "客户社会统一信用代码不能为空，请确认后重新请求");
            } else xmlIn.getBody().setCUSTOMER_CODE(CommonMethods.trimAllBlanks(customerCode));
            if (customerName == null || "".equals(customerName)) {
                throw new ResultException(ResultEnum.http_status_null_message, "客户名称不能为空，请确认后重新请求");
            } else xmlIn.getBody().setCUSTOMER_NAME(CommonMethods.trimAllBlanks(customerName));
            if (orderNo == null || "".equals(orderNo)) {
                throw new ResultException(ResultEnum.http_status_null_message, "订单编号不能为空，请确认后重新请求");
            } else xmlIn.getBody().setORDER_NO(CommonMethods.trimAllBlanks(orderNo));
            if (orderAmount == null || "".equals(orderAmount)) {
                throw new ResultException(ResultEnum.http_status_null_message, "订单金额不能为空，请确认后重新请求");
            } else orderAmount = CommonMethods.trimAllBlanks(orderAmount);
            if (orderDate == null || "".equals(orderDate)) {
                throw new ResultException(ResultEnum.http_status_null_message, "订单日期不能为空，请确认后重新请求");
            } else orderDate = CommonMethods.trimAllBlanks(orderDate);
            if (selfPay == null || "".equals(selfPay)) {
                throw new ResultException(ResultEnum.http_status_null_message, "自有资金付款金额不能为空，请确认后重新请求");
            } else selfPay = CommonMethods.trimAllBlanks(selfPay);
            if (selfPayRate == null || "".equals(selfPayRate)) {
                throw new ResultException(ResultEnum.http_status_null_message, "自有资金支付占比不能为空，请确认后重新请求");
            } else selfPayRate = CommonMethods.trimAllBlanks(selfPayRate);
            if (branchCompany == null || "".equals(branchCompany)) {
                throw new ResultException(ResultEnum.http_status_null_message, "所属分公司不能为空，请确认后重新请求");
            } else xmlIn.getBody().setBRACH_COMPANY(CommonMethods.trimAllBlanks(branchCompany));
            if (branchAccount == null || "".equals(branchAccount)) {
                throw new ResultException(ResultEnum.http_status_null_message, "分公司账户不能为空，请确认后重新请求");
            } else xmlIn.getBody().setBRACH_ACCOUNT(CommonMethods.trimAllBlanks(branchAccount));
            // 入参类型检查
            try {
                Double.parseDouble(orderAmount);
                xmlIn.getBody().setORDER_AMOUNT(orderAmount);
            } catch (Exception ex) {
                throw new ResultException(ResultEnum.http_status_not_number, "订单金额格式错误，请输入数字类型");
            }
            if (!CommonMethods.checkIsAllDate(orderDate)) {
                throw new ResultException(ResultEnum.http_status_date_message, "订单日期格式错误，格式为 yyyyMMddHHmmss");
            } else {
                xmlIn.getBody().setORDER_DATE(CommonMethods.changeDateFmt(orderDate, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
            }
            try {
                Double.parseDouble(selfPay);
                xmlIn.getBody().setSELF_PAY(selfPay);
            } catch (Exception ex) {
                throw new ResultException(ResultEnum.http_status_not_number, "自有资金付款金额格式错误，请输入数字类型");
            }
            try {
                Double.parseDouble(selfPayRate);
                xmlIn.getBody().setSELF_PAY_RATE(selfPayRate);
            } catch (Exception ex) {
                throw new ResultException(ResultEnum.http_status_not_number, "自有资金支付占比格式错误，请输入数字类型");
            }
            // 入参固定值判断
            // 入参长度检查
            if (channelCode.length() > 8)       throw new ResultException(ResultEnum.http_status_data_too_long, "接入机构号长度过长，请确认后重新请求");
            if (customerCode.length() > 100)    throw new ResultException(ResultEnum.http_status_data_too_long, "客户社会统一信用代码长度过长，请确认后重新请求");
            if (customerName.length() < 2)      throw new ResultException(ResultEnum.http_status_data_too_short, "客户名称长度过短，请确认后重新请求");
            if (customerName.length() > 100)    throw new ResultException(ResultEnum.http_status_data_too_long, "客户名称长度过长，请确认后重新请求");
            if (orderNo.length() > 100)         throw new ResultException(ResultEnum.http_status_data_too_long, "订单编号长度过长，请确认后重新请求");
            if (branchCompany.length() < 2)     throw new ResultException(ResultEnum.http_status_data_too_short, "所属分公司长度过短，请确认后重新请求");
            if (branchCompany.length() > 120)   throw new ResultException(ResultEnum.http_status_data_too_long, "所属分公司长度过长，请确认后重新请求");
            if (branchAccount.length() > 32)    throw new ResultException(ResultEnum.http_status_data_too_long, "分公司账户长度过长，请确认后重新请求");
            // 入参关联检查
            // 入参特殊字符
            if (CommonMethods.checkMySqlReservedWords(channelCode))    throw new ResultException(ResultEnum.http_status_data_special,"接入机构号不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(customerCode))    throw new ResultException(ResultEnum.http_status_data_special,"社会统一信用代码不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(customerName))    throw new ResultException(ResultEnum.http_status_data_special,"客户名称不能有特殊字符，请确认后重新请求");
            /*if (CommonMethods.checkMySqlReservedWords(branchCompany))   throw new ResultException(ResultEnum.http_status_data_special, "所属分公司不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(branchAccount))   throw new ResultException(ResultEnum.http_status_data_special, "分公司账户不能有特殊字符，请确认后重新请求");*/
            logger.info("参数验证完毕，进行后续业务处理");

            // 业务处理
            str = loanFinancingService.save(xmlIn);
            logger.info("--G353订单融资申请转发中证--END");
        }catch (Exception e){
            logger.info("--G353订单融资申请转发中证--ERROR");
            e.printStackTrace();
            BodyEntityMSG bodyEntity = new BodyEntityMSG();
            bodyEntity.setRESULT_FLAG("0");
            bodyEntity.setATTRIBUTE6("");
            bodyEntity.setATTRIBUTE7("");
            if (e instanceof NullPointerException || e instanceof JSONException || e instanceof ClassCastException){  /* 请求参数异常 */
                bodyEntity.setMSG(ResultEnum.http_status_request_null.getMsg());
            }else if(e instanceof ResultException){ /* 自定义异常 */
                bodyEntity.setMSG(e.getMessage());
            }else if (e instanceof BadSqlGrammarException){/*  数据库语法错误 */
                bodyEntity.setMSG(ResultEnum.http_status_sql_grammar_mistakes.getMsg());
            }else if (e instanceof MyBatisSystemException || e instanceof CannotCreateTransactionException){    /* 数据库连接异常*/
                bodyEntity.setMSG(ResultEnum.http_status_sql_connection.getMsg());
            }else if (e instanceof DuplicateKeyException){    /* 数据库主键唯一异常*/
                bodyEntity.setMSG(ResultEnum.http_status_sql_primary.getMsg());
            }else{ /* 其他系统异常 */
                bodyEntity.setMSG(ResultEnum.http_status_internal_server_error.getMsg());
            }
            BodyEntityMSGXMLOut out = new BodyEntityMSGXMLOut();   // 合集出参
            out.setBody(bodyEntity);
            out.setHead(xmlIn.getHead());
            return ResultUtil.getResult(out,BodyEntityMSGXMLOut.class);
        }
        return str;
    }

    /**
     *  G362 订单取消通知－中证开发
     *  光大银行将取消的订单信息通过此接口通知中证
     * @return
     */
    public String findOrderCancel(String req,OrderCancelService orderCancelService){
        logger.info("Controller--G362订单取消通知");
        String str = "";
        OrderCancelXMLIn xmlIn = new OrderCancelXMLIn();
        try {
            JaxbUtil ju = new JaxbUtil(OrderCancelXMLIn.class);
            xmlIn = ju.fromXml(req);

            // 接收参数
            String channelCode = xmlIn.getBody().getCHANNEL_CODE();
            String orderNo = xmlIn.getBody().getORDER_NO();
            String customerName = xmlIn.getBody().getCUSTOMER_NAME();
            String customerCode = xmlIn.getBody().getCUSTOMER_CODE();
            // 入参检查
            // 入参必须项检查
            if (channelCode == null || "".equals(channelCode)) {
                throw new ResultException(ResultEnum.http_status_null_message, "接入机构号不能为空，请确认后重新请求");
            } else xmlIn.getBody().setCHANNEL_CODE(CommonMethods.trimAllBlanks(channelCode));
            if (orderNo == null || "".equals(orderNo)) {
                throw new ResultException(ResultEnum.http_status_null_message, "订单编号不能为空，请确认后重新请求");
            } else xmlIn.getBody().setORDER_NO(CommonMethods.trimAllBlanks(orderNo));
            if (customerName == null || "".equals(customerName)) {
                throw new ResultException(ResultEnum.http_status_null_message, "客户名称不能为空，请确认后重新请求");
            } else xmlIn.getBody().setCUSTOMER_NAME(CommonMethods.trimAllBlanks(customerName));
            if (customerCode == null || "".equals(customerCode)) {
                throw new ResultException(ResultEnum.http_status_null_message, "客户社会统一信用代码不能为空，请确认后重新请求");
            } else xmlIn.getBody().setCUSTOMER_CODE(CommonMethods.trimAllBlanks(customerCode));
            // 入参类型检查
            // 入参长度检查
            if (channelCode.length() > 8)   throw new ResultException(ResultEnum.http_status_data_too_long, "接入机构号长度过长，请确认后重新请求");
            if (orderNo.length() > 30)  throw new ResultException(ResultEnum.http_status_data_too_long, "订单编号长度过长，请确认后重新请求");
            if (customerName.length() < 2)  throw new ResultException(ResultEnum.http_status_data_too_short, "客户名称长度过短，请确认后重新请求");
            if (customerName.length() > 120)    throw new ResultException(ResultEnum.http_status_data_too_long, "客户名称长度过长，请确认后重新请求");
            if (customerCode.length() > 30)     throw new ResultException(ResultEnum.http_status_data_too_long, "客户社会统一信用代码长度过长，请确认后重新请求");
            // 入参关联检查
            // 入参特殊字符
            if (CommonMethods.checkMySqlReservedWords(channelCode))    throw new ResultException(ResultEnum.http_status_data_special,"接入机构号不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(customerCode))    throw new ResultException(ResultEnum.http_status_data_special,"社会统一信用代码不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(customerName))    throw new ResultException(ResultEnum.http_status_data_special,"客户名称不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(orderNo))    throw new ResultException(ResultEnum.http_status_data_special, "订单编号不能有特殊字符，请确认后重新请求");
            logger.info("参数验证完毕，进行后续业务处理");

            // 业务处理
            str = orderCancelService.save(xmlIn);
            logger.info("--G362订单取消通知--END");
        }catch (Exception e){
            logger.info("--G362订单取消通知--ERROR");
            e.printStackTrace();
            BodyOrderCancelXMLOut out = new BodyOrderCancelXMLOut();   // 合集出参
            BodyOrderCancel bodyEntity = new BodyOrderCancel();   // 出参定义 Body
            bodyEntity.setRESULT_FLAG("0");
            if (e instanceof NullPointerException || e instanceof JSONException || e instanceof ClassCastException){  /* 请求参数异常 */
                bodyEntity.setMSG(ResultEnum.http_status_request_null.getMsg());
            }else if(e instanceof ResultException){ /* 自定义异常 */
                bodyEntity.setMSG(e.getMessage());
            }else if (e instanceof BadSqlGrammarException){/*  数据库语法错误 */
                bodyEntity.setMSG(ResultEnum.http_status_sql_grammar_mistakes.getMsg());
            }else if (e instanceof MyBatisSystemException || e instanceof CannotCreateTransactionException){    /* 数据库连接异常*/
                bodyEntity.setMSG(ResultEnum.http_status_sql_connection.getMsg());
            }else if (e instanceof DuplicateKeyException){    /* 数据库主键唯一异常*/
                bodyEntity.setMSG(ResultEnum.http_status_sql_primary.getMsg());
            }else{ /* 其他系统异常 */
                bodyEntity.setMSG(ResultEnum.http_status_internal_server_error.getMsg());
            }
            bodyEntity.setATTRIBUTE6("");
            bodyEntity.setATTRIBUTE7("");
            bodyEntity.setATTRIBUTE8("");
            bodyEntity.setATTRIBUTE9("");
            bodyEntity.setATTRIBUTE10("");
            out.setHead(xmlIn.getHead());
            out.setBody(bodyEntity);
            // 转换成xml格式
            return ResultUtil.getResult(out,BodyOrderCancelXMLOut.class);
        }
        return str;
    }
}