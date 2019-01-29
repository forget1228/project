package com.xj.ptgd.controller;

import com.xj.ptgd.common.exception.ResultException;
import com.xj.ptgd.common.result.ResultEnum;
import com.xj.ptgd.common.result.ResultUtil;
import com.xj.ptgd.common.util.CommonMethods;
import com.xj.ptgd.common.util.JaxbUtil;
import com.xj.ptgd.entity.body.BodyEntityMSG;
import com.xj.ptgd.entity.body.BodyEntityNc;
import com.xj.ptgd.entity.body.NoticeCs;
import com.xj.ptgd.entity.body.PushContract;
import com.xj.ptgd.entity.in.NoticeContractXMLIn;
import com.xj.ptgd.entity.in.PushContractXMLIn;
import com.xj.ptgd.entity.out.BodyEntityMSGXMLOut;
import com.xj.ptgd.entity.out.BodyEntityNcXMLOut;
import com.xj.ptgd.service.NoticeContractService;
import com.xj.ptgd.service.PushContractService;
import net.sf.json.JSONException;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.List;

public class ChinaCscsSelfThreeController {
    // 日志输出
    Logger logger = LoggerFactory.getLogger(ChinaCscsSelfThreeController.class);

    /**
     * 3.13.G390推送合同信息
     * 接口功能概述	供应链通过此接口接收中证推送《动产抵押合同》、《最高额保证合同》《信用增进服务协议》、《四方合作协议》信息字段
     * 调用说明	小微客户经理维护合同后 ，将通过此接口接收中证推送《动产抵押合同》、《最高额保证合同》《信用增进服务协议》、《四方合作协议》信息字段
     */
    public String pushContract(String req,PushContractService pushContractService){
        logger.info("Controller--G390推送合同信息");
        String str = "";
        PushContractXMLIn xml = new PushContractXMLIn();
        try {
            JaxbUtil ju = new JaxbUtil(PushContractXMLIn.class);
            xml = ju.fromXml(req);

            // 接收参数
            PushContract body = xml.getBody();
            String CHANNEL_CODE = body.getCHANNEL_CODE();                        // CHANNEL_CODE	接入机构号	VARCHAR(8)	Y	由银行定义
            String OPERATION_TYPE = body.getOPERATION_TYPE();                      // OPERATION_TYPE	操作类型	VARCHAR(1)	Y	1-新增 2-修改
            String REFUSAL_REASON = body.getREFUSAL_REASON();                      // REFUSAL_REASON	拒绝原因	VARCHAR(200)	N	操作类型为2时有值
            String CONTRACT_BATCH_NUMBER = body.getCONTRACT_BATCH_NUMBER();              // CONTRACT_BATCH_NUMBER	合同批次号	VARCHAR(30)	Y	唯一
            String CUSTOMER_CODE = body.getCUSTOMER_CODE();                       // CUSTOMER_CODE	客户社会统一信用代码	VARCHAR2(100)	Y	如客户无“社会信用代码”则输入“组织机构代码”
            String CUSTOMER_NAME = body.getCUSTOMER_NAME();                       // CUSTOMER_NAME	客户名称	VARCHAR2(100)	Y
            String AGREEMENT_NUMBER = body.getAGREEMENT_NUMBER();                    // AGREEMENT_NUMBER	四方协议编号	VARCHAR2(30)	Y
            String CUSTOMER_REPRESENTATIVE = body.getCUSTOMER_REPRESENTATIVE();            // CUSTOMER_REPRESENTATIVE	客户法定代表人	VARCHAR2(30)	Y
            String PRINCIPAL_AGENT = body.getPRINCIPAL_AGENT();                     // PRINCIPAL_AGENT	客户委托代理人	VARCHAR2(30)	Y
            String CUSTOMER_ADDRESS = body.getCUSTOMER_ADDRESS();                    // CUSTOMER_ADDRESS	客户地址	VARCHAR2(100)	Y
            String CUSTOMER_PHONE = body.getCUSTOMER_PHONE();                      // CUSTOMER_PHONE	客户电话	VARCHAR2(15)	Y
            String CONTRACT_NO = body.getCONTRACT_NO();                         // CONTRACT_NO	最高额保证合同编号	VARCHAR2(30)	Y
            String MAX_CREDIT_LINE = body.getMAX_CREDIT_LINE();                     // MAX_CREDIT_LINE	最高授信额度	NUMBER(16,2)	Y
            String CUSTOMER_REGISTRATION_ADDRESS = body.getCUSTOMER_REGISTRATION_ADDRESS();     // CUSTOMER_REGISTRATION_ADDRESS	客户注册地址	VARCHAR2(120)	Y
            String CUSTOMER_POSTAL_ADDRESS = body.getCUSTOMER_POSTAL_ADDRESS();            // CUSTOMER_POSTAL_ADDRESS	客户通讯地址	VARCHAR2(120)	Y
            String CUSTOMER_CONTACT = body.getCUSTOMER_CONTACT();                    // CUSTOMER_CONTACT	客户联系人	VARCHAR2(30)	Y
            String CONTACT_NUMBER = body.getCONTACT_NUMBER();                      // CONTACT_NUMBER	联系电话	VARCHAR2(20)	Y
            String CONTACT_FAX = body.getCONTACT_FAX();                         // CONTACT_FAX	联系传值	VARCHAR2(20)	Y
            String CONTACT_POSTAL_CODE = body.getCONTACT_POSTAL_CODE();                 // CONTACT_POSTAL_CODE	邮政编码	VARCHAR2(20)	Y
            String TOTAL_CREDIT = body.getTOTAL_CREDIT();                        // TOTAL_CREDIT	信用增信授信总额度	NUMBER(16,2)	Y

            // 入参检查
            // 入参必须项检查
            if (CHANNEL_CODE == null || "".equals(CHANNEL_CODE)) {
                throw new ResultException(ResultEnum.http_status_null_message, "接入机构号不能为空，请确认后重新请求");
            } else xml.getBody().setCHANNEL_CODE(CommonMethods.trimAllBlanks(CHANNEL_CODE));
            if (OPERATION_TYPE == null || "".equals(OPERATION_TYPE)) {
                throw new ResultException(ResultEnum.http_status_null_message, "操作类型不能为空，请确认后重新请求");
            }
            if (CONTRACT_BATCH_NUMBER == null || "".equals(CONTRACT_BATCH_NUMBER)) {
                throw new ResultException(ResultEnum.http_status_null_message, "合同批次号不能为空，请确认后重新请求");
            }else xml.getBody().setCONTRACT_BATCH_NUMBER(CommonMethods.trimAllBlanks(CONTRACT_BATCH_NUMBER));
            if (CUSTOMER_CODE == null || "".equals(CUSTOMER_CODE)) {
                throw new ResultException(ResultEnum.http_status_null_message, "客户社会统一信用代码不能为空，请确认后重新请求");
            }else xml.getBody().setCUSTOMER_CODE(CommonMethods.trimAllBlanks(CUSTOMER_CODE));
            if (CUSTOMER_NAME == null || "".equals(CUSTOMER_NAME)) {
                throw new ResultException(ResultEnum.http_status_null_message, "客户名称不能为空，请确认后重新请求");
            }else xml.getBody().setCUSTOMER_NAME(CommonMethods.trimAllBlanks(CUSTOMER_NAME));
            if (AGREEMENT_NUMBER == null || "".equals(AGREEMENT_NUMBER)) {
                throw new ResultException(ResultEnum.http_status_null_message, "四方协议编号不能为空，请确认后重新请求");
            }else xml.getBody().setAGREEMENT_NUMBER(CommonMethods.trimAllBlanks(AGREEMENT_NUMBER));
            if (CUSTOMER_REPRESENTATIVE == null || "".equals(CUSTOMER_REPRESENTATIVE)) {
                throw new ResultException(ResultEnum.http_status_null_message, "客户法定代表人不能为空，请确认后重新请求");
            }else xml.getBody().setCUSTOMER_REPRESENTATIVE(CommonMethods.trimAllBlanks(CUSTOMER_REPRESENTATIVE));
            if (PRINCIPAL_AGENT == null || "".equals(PRINCIPAL_AGENT)) {
                throw new ResultException(ResultEnum.http_status_null_message, "客户委托代理人不能为空，请确认后重新请求");
            }else xml.getBody().setPRINCIPAL_AGENT(CommonMethods.trimAllBlanks(PRINCIPAL_AGENT));
            if (CUSTOMER_ADDRESS == null || "".equals(CUSTOMER_ADDRESS)) {
                throw new ResultException(ResultEnum.http_status_null_message, "客户地址不能为空，请确认后重新请求");
            }else xml.getBody().setCUSTOMER_ADDRESS(CommonMethods.trimAllBlanks(CUSTOMER_ADDRESS));
            if (CUSTOMER_PHONE == null || "".equals(CUSTOMER_PHONE)) {
                throw new ResultException(ResultEnum.http_status_null_message, "客户电话不能为空，请确认后重新请求");
            }else xml.getBody().setCUSTOMER_PHONE(CommonMethods.trimAllBlanks(CUSTOMER_PHONE));
            if (CONTRACT_NO == null || "".equals(CONTRACT_NO)) {
                throw new ResultException(ResultEnum.http_status_null_message, "最高额保证合同编号不能为空，请确认后重新请求");
            }else xml.getBody().setCONTRACT_NO(CommonMethods.trimAllBlanks(CONTRACT_NO));
            if (MAX_CREDIT_LINE == null || "".equals(MAX_CREDIT_LINE)) {
                throw new ResultException(ResultEnum.http_status_null_message, "最高授信额度不能为空，请确认后重新请求");
            }else xml.getBody().setMAX_CREDIT_LINE(CommonMethods.trimAllBlanks(MAX_CREDIT_LINE));
            if (CUSTOMER_REGISTRATION_ADDRESS == null || "".equals(CUSTOMER_REGISTRATION_ADDRESS)) {
                throw new ResultException(ResultEnum.http_status_null_message, "客户注册地址不能为空，请确认后重新请求");
            }else xml.getBody().setCUSTOMER_REGISTRATION_ADDRESS(CommonMethods.trimAllBlanks(CUSTOMER_REGISTRATION_ADDRESS));
            if (CUSTOMER_POSTAL_ADDRESS == null || "".equals(CUSTOMER_POSTAL_ADDRESS)) {
                throw new ResultException(ResultEnum.http_status_null_message, "客户通讯地址不能为空，请确认后重新请求");
            }else xml.getBody().setCUSTOMER_POSTAL_ADDRESS(CommonMethods.trimAllBlanks(CUSTOMER_POSTAL_ADDRESS));
            if (CUSTOMER_CONTACT == null || "".equals(CUSTOMER_CONTACT)) {
                throw new ResultException(ResultEnum.http_status_null_message, "客户联系人不能为空，请确认后重新请求");
            }else xml.getBody().setCUSTOMER_CONTACT(CommonMethods.trimAllBlanks(CUSTOMER_CONTACT));
            if (CONTACT_NUMBER == null || "".equals(CONTACT_NUMBER)) {
                throw new ResultException(ResultEnum.http_status_null_message, "联系电话不能为空，请确认后重新请求");
            }else xml.getBody().setCONTACT_NUMBER(CommonMethods.trimAllBlanks(CONTACT_NUMBER));
            if (CONTACT_FAX == null || "".equals(CONTACT_FAX)) {
                throw new ResultException(ResultEnum.http_status_null_message, "联系传真不能为空，请确认后重新请求");
            }else xml.getBody().setCONTACT_FAX(CommonMethods.trimAllBlanks(CONTACT_FAX));
            if (CONTACT_POSTAL_CODE == null || "".equals(CONTACT_POSTAL_CODE)) {
                throw new ResultException(ResultEnum.http_status_null_message, "邮政编码不能为空，请确认后重新请求");
            }else xml.getBody().setCONTACT_POSTAL_CODE(CommonMethods.trimAllBlanks(CONTACT_POSTAL_CODE));
            if (TOTAL_CREDIT == null || "".equals(TOTAL_CREDIT)) {
                throw new ResultException(ResultEnum.http_status_null_message, "信用增信授信总额度不能为空，请确认后重新请求");
            }else xml.getBody().setTOTAL_CREDIT(CommonMethods.trimAllBlanks(TOTAL_CREDIT));
            // 入参类型检查
            if ("1".equals(OPERATION_TYPE)){
                xml.getBody().setOPERATION_TYPE(OPERATION_TYPE);
            }else if ("2".equals(OPERATION_TYPE)){
                xml.getBody().setOPERATION_TYPE(OPERATION_TYPE);
            }else {
                throw new ResultException(ResultEnum.http_status_data_fix,"操作类型不符，请确认后重新请求");
            }
            try {
                Double.parseDouble(MAX_CREDIT_LINE);
                xml.getBody().setMAX_CREDIT_LINE(MAX_CREDIT_LINE);
            } catch (Exception ex) {
                throw new ResultException(ResultEnum.http_status_not_number, "最高授信额度错误，请输入数字类型");
            }
            try {
                Double.parseDouble(TOTAL_CREDIT);
                xml.getBody().setTOTAL_CREDIT(TOTAL_CREDIT);
            } catch (Exception ex) {
                throw new ResultException(ResultEnum.http_status_not_number, "信用增信授信总额度错误，请输入数字类型");
            }

            // 入参长度检查
            if (CHANNEL_CODE.length() > 8)  throw new ResultException(ResultEnum.http_status_data_too_long, "接入机构号长度过长，请确认后重新请求");
            if (CONTRACT_BATCH_NUMBER.length() > 30)    throw new ResultException(ResultEnum.http_status_data_too_long, "合同批次号长度过长，请确认后重新请求");
            if (CUSTOMER_CODE.length() > 100)   throw new ResultException(ResultEnum.http_status_data_too_long, "客户社会统一信用代码长度过长，请确认后重新请求");
            if (CUSTOMER_NAME.length() > 100)   throw new ResultException(ResultEnum.http_status_data_too_long, "客户名称长度过长，请确认后重新请求");
            if (AGREEMENT_NUMBER.length() > 30)   throw new ResultException(ResultEnum.http_status_data_too_long, "四方协议编号长度过长，请确认后重新请求");
            if (CUSTOMER_REPRESENTATIVE.length() > 30)   throw new ResultException(ResultEnum.http_status_data_too_long, "客户法定代表人过长，请确认后重新请求");
            if (PRINCIPAL_AGENT.length() > 30)   throw new ResultException(ResultEnum.http_status_data_too_long, "客户委托代理人长度过长，请确认后重新请求");
            if (CUSTOMER_ADDRESS.length() > 100)   throw new ResultException(ResultEnum.http_status_data_too_long, "客户地址长度过长，请确认后重新请求");
            if (CUSTOMER_PHONE.length() > 15)   throw new ResultException(ResultEnum.http_status_data_too_long, "客户电话长度过长，请确认后重新请求");
            if (CONTRACT_NO.length() > 30)   throw new ResultException(ResultEnum.http_status_data_too_long, "最高额保证合同编号长度过长，请确认后重新请求");
            if (CUSTOMER_REGISTRATION_ADDRESS.length() > 120)   throw new ResultException(ResultEnum.http_status_data_too_long, "客户注册地址长度过长，请确认后重新请求");
            if (CUSTOMER_POSTAL_ADDRESS.length() > 120)   throw new ResultException(ResultEnum.http_status_data_too_long, "客户通讯地址长度过长，请确认后重新请求");
            if (CUSTOMER_CONTACT.length() > 30)   throw new ResultException(ResultEnum.http_status_data_too_long, "客户联系人长度过长，请确认后重新请求");
            if (CONTACT_NUMBER.length() > 20)   throw new ResultException(ResultEnum.http_status_data_too_long, "联系电话长度过长，请确认后重新请求");
            if (CONTACT_FAX.length() > 20)   throw new ResultException(ResultEnum.http_status_data_too_long, "联系传值长度过长，请确认后重新请求");
            if (CONTACT_POSTAL_CODE.length() > 20)   throw new ResultException(ResultEnum.http_status_data_too_long, "邮政编码长度过长，请确认后重新请求");

            // 入参关联检查
            if (CommonMethods.checkMySqlReservedWords(CHANNEL_CODE))    throw new ResultException(ResultEnum.http_status_data_special, "接入机构号不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(CONTRACT_BATCH_NUMBER))    throw new ResultException(ResultEnum.http_status_data_special, "合同批次号不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(CUSTOMER_CODE))    throw new ResultException(ResultEnum.http_status_data_special, "客户社会统一信用代码不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(CUSTOMER_NAME))    throw new ResultException(ResultEnum.http_status_data_special, "客户名称不能有特殊字符，请确认后重新请求");
            /*if (CommonMethods.checkMySqlReservedWords(AGREEMENT_NUMBER))    throw new ResultException(ResultEnum.http_status_data_special, "四方协议编号不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(CUSTOMER_REPRESENTATIVE))    throw new ResultException(ResultEnum.http_status_data_special, "客户法定代表人不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(PRINCIPAL_AGENT))    throw new ResultException(ResultEnum.http_status_data_special, "客户委托代理人不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(CUSTOMER_ADDRESS))    throw new ResultException(ResultEnum.http_status_data_special, "客户地址不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(CUSTOMER_PHONE))    throw new ResultException(ResultEnum.http_status_data_special, "客户电话长度不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(CONTRACT_NO))    throw new ResultException(ResultEnum.http_status_data_special, "最高额保证合同编号不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(CUSTOMER_REGISTRATION_ADDRESS))    throw new ResultException(ResultEnum.http_status_data_special, "客户注册地址不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(CUSTOMER_POSTAL_ADDRESS))    throw new ResultException(ResultEnum.http_status_data_special, "客户通讯地址不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(CUSTOMER_CONTACT))    throw new ResultException(ResultEnum.http_status_data_special, "客户联系人不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(CONTACT_NUMBER))    throw new ResultException(ResultEnum.http_status_data_special, "联系电话不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(CONTACT_FAX))    throw new ResultException(ResultEnum.http_status_data_special, "联系传值不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(CONTACT_POSTAL_CODE))    throw new ResultException(ResultEnum.http_status_data_special, "邮政编码不能有特殊字符，请确认后重新请求");*/
            if ("2".equals(OPERATION_TYPE)){
                if (REFUSAL_REASON == null || "".equals(REFUSAL_REASON)) {
                    throw new ResultException(ResultEnum.http_status_null_message, "拒绝原因不能为空，请确认后重新请求");
                } else xml.getBody().setREFUSAL_REASON(CommonMethods.trimAllBlanks(REFUSAL_REASON));

                if (REFUSAL_REASON.length() > 200)  throw new ResultException(ResultEnum.http_status_data_too_long, "拒绝原因长度过长，请确认后重新请求");

                //if (CommonMethods.checkMySqlReservedWords(REFUSAL_REASON))    throw new ResultException(ResultEnum.http_status_data_special, "拒绝原因不能有特殊字符，请确认后重新请求");
            }

            logger.info("参数验证完毕，进行后续业务处理");
            // 业务处理
            str = pushContractService.pushContract(xml);
            logger.info("--G390推送合同信息----END");
            return str;
        }catch (Exception ex){
            logger.info("--G390推送合同信息----ERROR");
            ex.printStackTrace();
            BodyEntityMSG bodyEntity = new BodyEntityMSG();
            bodyEntity.setRESULT_FLAG("0");
            if (ex instanceof NullPointerException || ex instanceof JSONException || ex instanceof ClassCastException){  /* 请求参数异常 */
                bodyEntity.setMSG(ResultEnum.http_status_request_null.getMsg());
            }else if(ex instanceof ResultException){ /* 自定义异常 */
                bodyEntity.setMSG(ex.getMessage());
            }else if (ex instanceof BadSqlGrammarException){/*  数据库语法错误 */
                bodyEntity.setMSG(ResultEnum.http_status_sql_grammar_mistakes.getMsg());
            }else if (ex instanceof MyBatisSystemException || ex instanceof CannotCreateTransactionException){    /* 数据库连接异常*/
                bodyEntity.setMSG(ResultEnum.http_status_sql_connection.getMsg());
            }else if (ex instanceof DuplicateKeyException){    /* 数据库主键唯一异常*/
                bodyEntity.setMSG(ResultEnum.http_status_sql_primary.getMsg());
            }else{ /* 其他系统异常 */
                bodyEntity.setMSG(ResultEnum.http_status_internal_server_error.getMsg());
            }
            bodyEntity.setATTRIBUTE6("");
            bodyEntity.setATTRIBUTE7("");
            BodyEntityMSGXMLOut out = new BodyEntityMSGXMLOut();   // 合集出参
            out.setBody(bodyEntity);
            out.setHead(xml.getHead());
            return ResultUtil.getResult(out,BodyEntityMSGXMLOut.class);
        }
    }

    /**
     * 3.15.G392 合同生效通知
     * 接口功能概述	合同生效后，供应链生成PDF文件，通过此接口将文件名称告知中证
     * 调用说明	合同生效后，供应链生成PDF文件，通过此接口将文件名称告知中证
     * @param req
     * @param noticeContractService
     * @return
     */
    public String noticeContract(String req,NoticeContractService noticeContractService){
        logger.info("Controller--G392合同生效通知");
        String str = "";
        NoticeContractXMLIn xml = new NoticeContractXMLIn();
        try {
            JaxbUtil ju = new JaxbUtil(NoticeContractXMLIn.class);
            xml = ju.fromXml(req);

            // 接收参数
            String CHANNEL_CODE = xml.getBody().getCHANNEL_CODE();
            String CUSTOMER_CODE = xml.getBody().getCUSTOMER_CODE();
            String CUSTOMER_NAME = xml.getBody().getCUSTOMER_NAME();
            String CONTRACT_BATCH_NUMBER = xml.getBody().getCONTRACT_BATCH_NUMBER();
            List<NoticeCs> LIST_OBJ = xml.getBody().getLIST_OBJ();
            // 入参检查
            // 入参必须项检查
            if (CHANNEL_CODE == null || "".equals(CHANNEL_CODE)){
                throw new ResultException(ResultEnum.http_status_null_message,"接入机构号不能为空，请确认后重新请求");
            }else xml.getBody().setCHANNEL_CODE(CommonMethods.trimAllBlanks(CHANNEL_CODE));
            if (CUSTOMER_CODE == null || "".equals(CUSTOMER_CODE)){
                throw new ResultException(ResultEnum.http_status_null_message,"客户社会统一信用代码不能为空，请确认后重新请求");
            }else xml.getBody().setCUSTOMER_CODE(CommonMethods.trimAllBlanks(CUSTOMER_CODE));
            if (CUSTOMER_NAME == null || "".equals(CUSTOMER_NAME)){
                throw new ResultException(ResultEnum.http_status_null_message,"客户名称不能为空，请确认后重新请求");
            }else xml.getBody().setCUSTOMER_NAME(CommonMethods.trimAllBlanks(CUSTOMER_NAME));
            if (CONTRACT_BATCH_NUMBER == null || "".equals(CONTRACT_BATCH_NUMBER)){
                throw new ResultException(ResultEnum.http_status_null_message,"批次号不能为空，请确认后重新请求");
            }else xml.getBody().setCONTRACT_BATCH_NUMBER(CommonMethods.trimAllBlanks(CONTRACT_BATCH_NUMBER));
            // 入参类型检查
            // 入参长度检查
            if (CHANNEL_CODE.length() > 8)  throw new ResultException(ResultEnum.http_status_data_too_long, "接入机构号长度过长，请确认后重新请求");
            if (CUSTOMER_CODE.length() > 100)  throw new ResultException(ResultEnum.http_status_data_too_long, "客户社会统一信用代码长度过长，请确认后重新请求");
            if (CUSTOMER_NAME.length() > 100)  throw new ResultException(ResultEnum.http_status_data_too_long, "客户名称长度过长，请确认后重新请求");
            if (CONTRACT_BATCH_NUMBER.length() > 30)  throw new ResultException(ResultEnum.http_status_data_too_long, "批次号长度过长，请确认后重新请求");
            // 入参关联检查
            if (CommonMethods.checkMySqlReservedWords(CHANNEL_CODE))    throw new ResultException(ResultEnum.http_status_data_special, "接入机构号不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(CUSTOMER_CODE))    throw new ResultException(ResultEnum.http_status_data_special, "客户社会统一信用代码不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(CUSTOMER_NAME))    throw new ResultException(ResultEnum.http_status_data_special, "客户名称不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(CONTRACT_BATCH_NUMBER))    throw new ResultException(ResultEnum.http_status_data_special, "批次号不能有特殊字符，请确认后重新请求");

            if (LIST_OBJ != null){
                for (NoticeCs file:LIST_OBJ){
                    String FILE_NAME = file.getFILE_NAME();
                    String FILE_PATH = file.getFILE_PATH();
                    String CONTRACT_TYPE = file.getCONTRACT_TYPE();
                    if (FILE_NAME == null || "".equals(FILE_NAME)){
                        throw new ResultException(ResultEnum.http_status_null_message,"文件名不能为空，请确认后重新请求");
                    }
                    if (FILE_PATH == null || "".equals(FILE_PATH)){
                        throw new ResultException(ResultEnum.http_status_null_message,"文件路径不能为空，请确认后重新请求");
                    }
                    if (CONTRACT_TYPE == null || "".equals(CONTRACT_TYPE)){
                        throw new ResultException(ResultEnum.http_status_null_message,"合同类型不能为空，请确认后重新请求");
                    }

                    if (!"1".equals(CONTRACT_TYPE) && !"2".equals(CONTRACT_TYPE) && !"3".equals(CONTRACT_TYPE) && !"4".equals(CONTRACT_TYPE)){
                        throw new ResultException(ResultEnum.http_status_data_fix,"合同类型不在‘1-4’范围内，请确认后重新请求");
                    }
                    if (FILE_NAME.length() > 200)  throw new ResultException(ResultEnum.http_status_data_too_long, "文件名长度过长，请确认后重新请求");
                    if (FILE_PATH.length() > 200)  throw new ResultException(ResultEnum.http_status_data_too_long, "文件路径长度过长，请确认后重新请求");

                    /*if (CommonMethods.checkMySqlReservedWords(FILE_NAME))    throw new ResultException(ResultEnum.http_status_data_special, "文件名不能有特殊字符，请确认后重新请求");
                    if (CommonMethods.checkMySqlReservedWords(FILE_PATH))    throw new ResultException(ResultEnum.http_status_data_special, "文件路径不能有特殊字符，请确认后重新请求");*/

                }
            } else throw new ResultException(ResultEnum.http_status_data_too_long, "合同文件信息无数据，请确认后重新请求");

            logger.info("参数验证完毕，进行后续业务处理");

            // 业务处理
            str = noticeContractService.noticeContract(xml);
            /*if ("error".equals(str)){
                throw new ResultException(ResultEnum.http_status_shell_error,ResultEnum.http_status_shell_error.getMsg());
            }else if ("2".equals(str)){
                throw new ResultException(ResultEnum.http_status_shell_error,"远程文件不存在");
            }else if ("3".equals(str)){
                throw new ResultException(ResultEnum.http_status_shell_error,"本地文件下载失败");
            }*/
            logger.info("--G392合同生效通知--END");
            return str;
        }catch (Exception ex){
            logger.info("--G392合同生效通知--ERROR");
            ex.printStackTrace();
            BodyEntityNc bodyEntity = new BodyEntityNc();
            bodyEntity.setRESULT_FLAG("0");
            if (ex instanceof NullPointerException || ex instanceof JSONException || ex instanceof ClassCastException){  /* 请求参数异常 */
                bodyEntity.setMSG(ResultEnum.http_status_request_null.getMsg());
            }else if(ex instanceof ResultException){ /* 自定义异常 */
                bodyEntity.setMSG(ex.getMessage());
            }else if (ex instanceof BadSqlGrammarException){/*  数据库语法错误 */
                bodyEntity.setMSG(ResultEnum.http_status_sql_grammar_mistakes.getMsg());
            }else if (ex instanceof MyBatisSystemException){    /* 数据库连接异常*/
                bodyEntity.setMSG(ResultEnum.http_status_sql_connection.getMsg());
            }else if (ex instanceof CannotCreateTransactionException){    /* 数据库连接异常*/
                bodyEntity.setMSG(ResultEnum.http_status_sql_connection.getMsg());
            }else if (ex instanceof DuplicateKeyException){    /* 数据库主键唯一异常*/
                bodyEntity.setMSG(ResultEnum.http_status_sql_primary.getMsg());
            }else{ /* 其他系统异常 */
                bodyEntity.setMSG(ResultEnum.http_status_internal_server_error.getMsg());
            }
            bodyEntity.setATTRIBUTE11("");
            bodyEntity.setATTRIBUTE12("");
            BodyEntityNcXMLOut out = new BodyEntityNcXMLOut();   // 合集出参
            out.setBody(bodyEntity);
            out.setHead(xml.getHead());
            return ResultUtil.getResult(out,BodyEntityNcXMLOut.class);
        }
    }
}
