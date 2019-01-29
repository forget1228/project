package com.xj.ptgd.controller;

import com.xj.ptgd.common.exception.ResultException;
import com.xj.ptgd.common.result.ResultEnum;
import com.xj.ptgd.common.result.ResultUtil;
import com.xj.ptgd.common.util.CommonMethods;
import com.xj.ptgd.common.util.JaxbUtil;
import com.xj.ptgd.entity.body.BodyEntity;
import com.xj.ptgd.entity.body.BodyEntityMSG;
import com.xj.ptgd.entity.in.*;
import com.xj.ptgd.entity.out.BodyEntityMSGXMLOut;
import com.xj.ptgd.entity.out.BodyEntityXMLOut;
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
public class ChinaCscsSelfSecondController {
    // 日志输出
    private Logger logger = LoggerFactory.getLogger(ChinaCscsSelfSecondController.class);

    /**
     * G371 推送经销商数据清单－中证开发（字段待光大确认）
     * @param req
     * @return
     */
    public String findDealer(String req,DealerDataService dealerDataService){
        logger.info("Controller--G371推送经销商数据清单");
        // 业务处理
        String str = "";
        DealerXMLIn xmlIn = new DealerXMLIn();
        try {
            JaxbUtil ju = new JaxbUtil(DealerXMLIn.class);
            xmlIn=ju.fromXml(req);

            // 接收参数
            String cNo = xmlIn.getBody().getCUST_NO();              				        // 客户编号
            String cName = xmlIn.getBody().getCUST_NAME();            				        // 客户名称
            String cSortName = xmlIn.getBody().getCUST_SORT_NAME();        				    // 客户简称
            String licenceNo = xmlIn.getBody().getLICENCE_NO();           				    // 营业执照
            String customerCode = xmlIn.getBody().getCUSTOMER_CODE();        				// 统一社会代码
            String hwStoreNumber = xmlIn.getBody().getHUAWEI_EXPERIENCE_STORE_NUMBER();     // 华为体验店数量
            String storeNumber = xmlIn.getBody().getSTORE_NUMBER();          	            // 门店数量
            String legalCom = xmlIn.getBody().getLEGAL_COMMISS();      	                    // 法定代表人姓名
            String ceCateType = xmlIn.getBody().getCERTIFICATE_TYPE();       	            // 证件类型
            String ceNumber = xmlIn.getBody().getCERTIFICATE_NUMBER();          	        // 证件号码
            String owPhone = xmlIn.getBody().getOWNER_PHONENO();                	        // 手机号
            String branchCompany = xmlIn.getBody().getBRACH_COMPANY();                      // 分公司
            String manageProvince = xmlIn.getBody().getMANAGE_PROVINCE();                   // 经营省份
            String manageCity = xmlIn.getBody().getMANAGE_CITY();                           // 经营地市
            String customerType = xmlIn.getBody().getCUSTOMER_TYPE();                       // 客户分类
            String tradingVoYear = xmlIn.getBody().getTRADING_VOLUME_YEAR();                // 近2年年化交易额
            String tradingVoMonth = xmlIn.getBody().getTRADING_VOLUME_MONTH();              // 近2年月均交易额
            String tradingTiYear = xmlIn.getBody().getTRADING_TIMES_YEAR();                 // 近2年年化交易频次
            String tradingTiMonth = xmlIn.getBody().getTRADING_TIMES_MONTH();               // 近2年月均交易频次
            String singleTradingAmount = xmlIn.getBody().getSINGLE_TRADING_AMOUNT();        // 近2年平均单笔交易额
            String hwTradingVo = xmlIn.getBody().getTRADING_TIMES_MONTH();                  // 近2年华为品牌交易额
            String hwTradingVoMonth = xmlIn.getBody().getHUAWEI_TRADING_VOLUME_MONTH();     // 近2年华为品牌月均交易额
            String hwBrandRate = xmlIn.getBody().getHUAWEI_BRAND_RATE();                    // 近2年华为品牌提货占比

            String channelCode = xmlIn.getBody().getCHANNEL_CODE();                    // 接入机构号

            // 入参检查     // 入参必须项检查
            if (cNo == null || "".equals(cNo)) throw new ResultException(ResultEnum.http_status_null_message,"客户编号不能为空，请确认后重新请求");
            else xmlIn.getBody().setCUST_NO(CommonMethods.trimAllBlanks(cNo));
            if (cName == null || "".equals(cName)) throw new ResultException(ResultEnum.http_status_null_message,"客户名称不能为空，请确认后重新请求");
            else xmlIn.getBody().setCUST_NAME(CommonMethods.trimAllBlanks(cName));
            if (cSortName == null || "".equals(cSortName)) throw new ResultException(ResultEnum.http_status_null_message,"客户简称不能为空，请确认后重新请求");
            else xmlIn.getBody().setCUST_SORT_NAME(CommonMethods.trimAllBlanks(cSortName));

            if (licenceNo != null && !"".equals(licenceNo)){
                xmlIn.getBody().setLICENCE_NO(CommonMethods.trimAllBlanks(licenceNo));
            }else if (customerCode != null && !"".equals(customerCode)){
                xmlIn.getBody().setCUSTOMER_CODE(CommonMethods.trimAllBlanks(customerCode));
            }else{
                throw new ResultException(ResultEnum.http_status_null_message,"营业执照、统一社会代码两者必须有一个不能为空，请确认后重新请求");
            }

            if (hwStoreNumber == null || "".equals(hwStoreNumber)) throw new ResultException(ResultEnum.http_status_null_message,"华为体验店数量不能为空，请确认后重新请求");
            else hwStoreNumber = CommonMethods.trimAllBlanks(hwStoreNumber);
            if (storeNumber == null || "".equals(storeNumber)) throw new ResultException(ResultEnum.http_status_null_message,"门店数量不能为空，请确认后重新请求");
            else storeNumber = CommonMethods.trimAllBlanks(storeNumber);
            if (legalCom == null || "".equals(legalCom)) throw new ResultException(ResultEnum.http_status_null_message,"法定代表人姓名不能为空，请确认后重新请求");
            else xmlIn.getBody().setLEGAL_COMMISS(CommonMethods.trimAllBlanks(legalCom));
            if (ceCateType == null || "".equals(ceCateType)) throw new ResultException(ResultEnum.http_status_null_message,"证件类型不能为空，请确认后重新请求");

            if (ceNumber == null || "".equals(ceNumber)) throw new ResultException(ResultEnum.http_status_null_message,"证件号码不能为空，请确认后重新请求");
            else xmlIn.getBody().setCERTIFICATE_NUMBER(CommonMethods.trimAllBlanks(ceNumber));
            if (owPhone == null || "".equals(owPhone)) throw new ResultException(ResultEnum.http_status_null_message,"手机号不能为空，请确认后重新请求");
            else xmlIn.getBody().setOWNER_PHONENO(CommonMethods.trimAllBlanks(owPhone));
            if (branchCompany == null || "".equals(branchCompany)) throw new ResultException(ResultEnum.http_status_null_message,"分公司不能为空，请确认后重新请求");
            else xmlIn.getBody().setBRACH_COMPANY(CommonMethods.trimAllBlanks(branchCompany));
            if (manageProvince == null || "".equals(manageProvince)) throw new ResultException(ResultEnum.http_status_null_message,"经营省份不能为空，请确认后重新请求");
            else xmlIn.getBody().setMANAGE_PROVINCE(CommonMethods.trimAllBlanks(manageProvince));
            if (manageCity == null || "".equals(manageCity)) throw new ResultException(ResultEnum.http_status_null_message,"经营地市不能为空，请确认后重新请求");
            else xmlIn.getBody().setMANAGE_CITY(CommonMethods.trimAllBlanks(manageCity));
            if (customerType == null || "".equals(customerType)) throw new ResultException(ResultEnum.http_status_null_message,"客户分类不能为空，请确认后重新请求");
            else xmlIn.getBody().setCUSTOMER_TYPE(CommonMethods.trimAllBlanks(customerType));
            if (tradingVoYear == null || "".equals(tradingVoYear)) throw new ResultException(ResultEnum.http_status_null_message,"近2年年化交易额不能为空，请确认后重新请求");
            else tradingVoYear = CommonMethods.trimAllBlanks(tradingVoYear);
            if (tradingVoMonth == null || "".equals(tradingVoMonth)) throw new ResultException(ResultEnum.http_status_null_message,"近2年月均交易额不能为空，请确认后重新请求");
            else tradingVoMonth = CommonMethods.trimAllBlanks(tradingVoMonth);
            if (tradingTiYear == null || "".equals(tradingTiYear)) throw new ResultException(ResultEnum.http_status_null_message,"近2年年化交易频次不能为空，请确认后重新请求");
            else tradingTiYear = CommonMethods.trimAllBlanks(tradingTiYear);
            if (tradingTiMonth == null || "".equals(tradingTiMonth)) throw new ResultException(ResultEnum.http_status_null_message,"近2年月均交易频次不能为空，请确认后重新请求");
            else tradingTiMonth = CommonMethods.trimAllBlanks(tradingTiMonth);
            if (singleTradingAmount == null || "".equals(singleTradingAmount)) throw new ResultException(ResultEnum.http_status_null_message,"近2年平均单笔交易额不能为空，请确认后重新请求");
            else singleTradingAmount = CommonMethods.trimAllBlanks(singleTradingAmount);
            if (hwTradingVo == null || "".equals(hwTradingVo)) throw new ResultException(ResultEnum.http_status_null_message,"近2年华为品牌交易额不能为空，请确认后重新请求");
            else hwTradingVo = CommonMethods.trimAllBlanks(hwTradingVo);
            if (hwTradingVoMonth == null || "".equals(hwTradingVoMonth)) throw new ResultException(ResultEnum.http_status_null_message,"近2年华为品牌月均交易额不能为空，请确认后重新请求");
            else hwTradingVoMonth = CommonMethods.trimAllBlanks(hwTradingVoMonth);
            if (hwBrandRate == null || "".equals(hwBrandRate)) throw new ResultException(ResultEnum.http_status_null_message,"近2年华为品牌提货占比不能为空，请确认后重新请求");
            else hwBrandRate = CommonMethods.trimAllBlanks(hwBrandRate);
            if (channelCode == null || "".equals(channelCode)) throw new ResultException(ResultEnum.http_status_null_message,"接入机构号不能为空，请确认后重新请求");
            else xmlIn.getBody().setCHANNEL_CODE(CommonMethods.trimAllBlanks(channelCode));

            // 入参类型检查
            // 1-身份证 2-香港  3-港澳  4-台湾  5-护照  6- 外籍
            ceCateType = CommonMethods.trimAllBlanks(ceCateType);
            if ("1".equals(ceCateType)){    // 1-身份证
                ceCateType = "Ind01"; // Ind01	身份证
            }else if ("2".equals(ceCateType)){    // 2-香港
                ceCateType = "Ind12"; // Ind12	香港身份证
            }else if ("3".equals(ceCateType)){    // 3-港澳
                ceCateType = "Ind06"; // Ind06	港澳居民来往内地通行证
            }else if ("4".equals(ceCateType)){    // 4-台湾
                ceCateType = "Ind14"; // Ind14	台湾身份证
            }else if ("5".equals(ceCateType)){    // 5-护照
                ceCateType = "Ind03"; // Ind03	护照
            }else if ("6".equals(ceCateType)){    // 6- 外籍
                ceCateType = "Ind09";     // Ind09	外国人居留证
            }else {
                throw new ResultException(ResultEnum.http_status_data_fix,"证件类型不在‘1-6’范围内，请确认后重新请求");
            }
            xmlIn.getBody().setCERTIFICATE_TYPE(ceCateType);
            try {
                Integer.parseInt(hwStoreNumber);
                xmlIn.getBody().setHUAWEI_EXPERIENCE_STORE_NUMBER(hwStoreNumber);
            }catch(Exception ex){
                throw new ResultException(ResultEnum.http_status_not_number,"华为体验店数量格式错误，请输入数字类型");
            }
            try {
                Integer.parseInt(storeNumber);
                xmlIn.getBody().setSTORE_NUMBER(storeNumber);
            }catch(Exception ex){
                throw new ResultException(ResultEnum.http_status_not_number,"门店数量格式错误，请输入数字类型");
            }
            try {
                Double.parseDouble(tradingVoYear);
                xmlIn.getBody().setTRADING_VOLUME_YEAR(tradingVoYear);
            }catch(Exception ex){
                throw new ResultException(ResultEnum.http_status_not_number,"近2年年化交易额格式错误，请输入数字类型");
            }
            try {
                Double.parseDouble(tradingVoMonth);
                xmlIn.getBody().setTRADING_VOLUME_MONTH(tradingVoMonth);
            }catch(Exception ex){
                throw new ResultException(ResultEnum.http_status_not_number,"近2年月均交易额格式错误，请输入数字类型");
            }
            try {
                Integer.parseInt(tradingTiYear);
                xmlIn.getBody().setTRADING_TIMES_YEAR(tradingTiYear);
            }catch(Exception ex){
                throw new ResultException(ResultEnum.http_status_not_number,"近2年年化交易频次格式错误，请输入数字类型");
            }
            try {
                Integer.parseInt(tradingTiMonth);
                xmlIn.getBody().setTRADING_TIMES_MONTH(tradingTiMonth);
            }catch(Exception ex){
                throw new ResultException(ResultEnum.http_status_not_number,"近2年月均交易频次格式错误，请输入数字类型");
            }
            try {
                Double.parseDouble(singleTradingAmount);
                xmlIn.getBody().setSINGLE_TRADING_AMOUNT(singleTradingAmount);
            }catch(Exception ex){
                throw new ResultException(ResultEnum.http_status_not_number,"近2年平均单笔交易额格式错误，请输入数字类型");
            }
            try {
                Double.parseDouble(hwTradingVo);
                xmlIn.getBody().setHUAWEI_TRADING_VOLUME(hwTradingVo);
            }catch(Exception ex){
                throw new ResultException(ResultEnum.http_status_not_number,"近2年华为品牌交易额格式错误，请输入数字类型");
            }
            try {
                Double.parseDouble(hwTradingVoMonth);
                xmlIn.getBody().setHUAWEI_TRADING_VOLUME_MONTH(hwTradingVoMonth);
            }catch(Exception ex){
                throw new ResultException(ResultEnum.http_status_not_number,"近2年华为品牌月均交易额格式错误，请输入数字类型");
            }
            try {
                Double.parseDouble(hwBrandRate);
                xmlIn.getBody().setHUAWEI_BRAND_RATE(hwBrandRate);
            }catch(Exception ex){
                throw new ResultException(ResultEnum.http_status_not_number,"近2年华为品牌提货占比格式错误，请输入数字类型");
            }
            // 入参长度检查
            if (cNo.length() > 30)          throw new ResultException(ResultEnum.http_status_data_too_long,"客户编号长度过长，请确认后重新请求");
            if (cName.length() < 2)       throw new ResultException(ResultEnum.http_status_data_too_short,"客户名称长度过短，请确认后重新请求");
            if (cName.length() > 100)       throw new ResultException(ResultEnum.http_status_data_too_long,"客户名称长度过长，请确认后重新请求");
            if (cSortName.length() < 2)       throw new ResultException(ResultEnum.http_status_data_too_short,"客户简称长度过短，请确认后重新请求");
            if (cSortName.length() > 100)   throw new ResultException(ResultEnum.http_status_data_too_long,"客户简称长度过长，请确认后重新请求");
            if (licenceNo.length() > 50)    throw new ResultException(ResultEnum.http_status_data_too_long,"营业执照长度过长，请确认后重新请求");
            if (customerCode.length() > 30) throw new ResultException(ResultEnum.http_status_data_too_long,"统一社会代码长度过长，请确认后重新请求");
            if (legalCom.length() > 100)    throw new ResultException(ResultEnum.http_status_data_too_long,"法定代表人姓名长度过长，请确认后重新请求");
            if (ceNumber.length() > 30)     throw new ResultException(ResultEnum.http_status_data_too_long,"证件号码长度过长，请确认后重新请求");
            if (owPhone.length() > 11)      throw new ResultException(ResultEnum.http_status_data_too_long,"手机号长度过长，请确认后重新请求");
            if (branchCompany.length() < 2)       throw new ResultException(ResultEnum.http_status_data_too_short,"分公司长度过短，请确认后重新请求");
            if (branchCompany.length() > 120)   throw new ResultException(ResultEnum.http_status_data_too_long,"分公司长度过长，请确认后重新请求");
            if (manageProvince.length() < 2)       throw new ResultException(ResultEnum.http_status_data_too_short,"经营省份长度过短，请确认后重新请求");
            if (manageProvince.length() > 50)   throw new ResultException(ResultEnum.http_status_data_too_long,"经营省份长度过长，请确认后重新请求");
            if (manageCity.length() < 2)       throw new ResultException(ResultEnum.http_status_data_too_short,"经营地市长度过短，请确认后重新请求");
            if (manageCity.length() > 50)       throw new ResultException(ResultEnum.http_status_data_too_long,"经营地市长度过长，请确认后重新请求");
            if (customerType.length() > 30)     throw new ResultException(ResultEnum.http_status_data_too_long,"客户分类长度过长，请确认后重新请求");
            // 入参关联检查
            // 入参特殊字符
            if (CommonMethods.checkMySqlReservedWords(cNo))         throw new ResultException(ResultEnum.http_status_data_special,"客户编号不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(channelCode))    throw new ResultException(ResultEnum.http_status_data_special,"接入机构号不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(cName))    throw new ResultException(ResultEnum.http_status_data_special,"客户名称不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(cSortName))   throw new ResultException(ResultEnum.http_status_data_special,"客户简称不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(licenceNo))   throw new ResultException(ResultEnum.http_status_data_special,"营业执照不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(customerCode))throw new ResultException(ResultEnum.http_status_data_special,"统一社会代码不能有特殊字符，请确认后重新请求");
            /*if (CommonMethods.checkMySqlReservedWords(legalCom))    throw new ResultException(ResultEnum.http_status_data_special,"法定代表人姓名不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(ceCateType))  throw new ResultException(ResultEnum.http_status_data_special,"证件类型不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(ceNumber))    throw new ResultException(ResultEnum.http_status_data_special,"证件号码不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(branchCompany))   throw new ResultException(ResultEnum.http_status_data_special,"分公司不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(manageProvince))  throw new ResultException(ResultEnum.http_status_data_special,"经营省份不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(manageCity))      throw new ResultException(ResultEnum.http_status_data_special,"经营地市不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(customerType))    throw new ResultException(ResultEnum.http_status_data_special,"客户分类不能有特殊字符，请确认后重新请求");*/
            logger.info("参数验证完毕，进行后续业务处理");
            str = dealerDataService.pushDealerData(xmlIn);
            if ("G371001".equals(str)){
                throw new ResultException(ResultEnum.http_status_data_fix,"客户分类不符合经销商类别准入条件，请检查后重新请求");
            }else if ("G371002".equals(str)){
                throw new ResultException(ResultEnum.http_status_data_fix,"手机号不符合反欺诈手机评分准入条件，请检查后重新请求");
            }else if ("G371003".equals(str)){
                throw new ResultException(ResultEnum.http_status_data_fix,"统一社会信用代码不符合统一社会信用代码准入条件，请检查后重新请求");
            }else if ("fail".equals(str)){
                throw new ResultException(ResultEnum.http_status_bad_request,"请求失败，外部接口失败，请检查后重新请求");
            }
            logger.info("--G371推送经销商数据清单--END");
            return str;
        }catch (Exception e){
            logger.info("--G371推送经销商数据清单--ERROR");
            e.printStackTrace();
            BodyEntityMSG bodyEntity = new BodyEntityMSG();
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
            BodyEntityMSGXMLOut out = new BodyEntityMSGXMLOut();   // 合集出参
            out.setBody(bodyEntity);
            out.setHead(xmlIn.getHead());
            return ResultUtil.getResult(out,BodyEntityMSGXMLOut.class);
        }
    }

    /**
     * G373 放款通知推送
     * 接口功能概述	供应链将放款通知信息推送至中证
     * 调用说明	供应链发起放款申请后，网贷易放款交易将放款信息返回给供应链，供应链更新订单状态并推送通知
     */
    public String loanNotice(String req,LoanNoticeService service){
        logger.info("Controller--G373放款通知推送中证");
        String str = "";
        LoanNoticeXMLIn xmlIn = new LoanNoticeXMLIn();
        try {
            JaxbUtil ju = new JaxbUtil(LoanNoticeXMLIn.class);
            xmlIn = ju.fromXml(req);

            // 接收参数
            String channelCode = xmlIn.getBody().getCHANNEL_CODE();
            String customerCode = xmlIn.getBody().getCUSTOMER_CODE();
            String customerName = xmlIn.getBody().getCUSTOMER_NAME();
            String orderNo = xmlIn.getBody().getORDER_NO();
            String orderStatus = xmlIn.getBody().getORDER_STATUS();
            String orderReason = xmlIn.getBody().getREJECT_REASON();
            String executeRate = xmlIn.getBody().getEXECUTE_RATE();
            String loUsNo = xmlIn.getBody().getLOUS_NO();
            String loanMoney = xmlIn.getBody().getLOAN_MONEY();
            String loanStartDate = xmlIn.getBody().getLOAN_START_DATE();
            String loanEndDate = xmlIn.getBody().getLOAN_END_DATE();
            // 入参检查
            // 入参必须项检查
            if (channelCode == null || "".equals(channelCode)) {
                throw new ResultException(ResultEnum.http_status_null_message, "接入机构号不能为空，请确认后重新请求");
            } else xmlIn.getBody().setCHANNEL_CODE(CommonMethods.trimAllBlanks(channelCode));
            if (customerCode == null || "".equals(customerCode)) {
                throw new ResultException(ResultEnum.http_status_null_message, "客户社会统一信用代码不能为空，请确认后重新请求");
            } else xmlIn.getBody().setCUSTOMER_CODE(CommonMethods.trimAllBlanks(customerCode));
            if (customerName == null || "".equals(customerName)) {
                throw new ResultException(ResultEnum.http_status_null_message, "企业名称不能为空，请确认后重新请求");
            } else xmlIn.getBody().setCUSTOMER_NAME(CommonMethods.trimAllBlanks(customerName));
            if (orderNo == null || "".equals(orderNo)) {
                throw new ResultException(ResultEnum.http_status_null_message, "订单编号不能为空，请确认后重新请求");
            } else xmlIn.getBody().setORDER_NO(CommonMethods.trimAllBlanks(orderNo));
            if (orderStatus == null || "".equals(orderStatus)) {
                throw new ResultException(ResultEnum.http_status_null_message, "订单状态不能为空，请确认后重新请求");
            } else orderStatus = CommonMethods.trimAllBlanks(orderStatus);

            // 入参类型检查
            if (!"4".equals(orderStatus) && !"9".equals(orderStatus)) {
                throw new ResultException(ResultEnum.http_status_data_fix, "订单状态错误，请输入固定值:'4'-已放款;'9'-放款失败");
            } else {
                xmlIn.getBody().setORDER_STATUS(orderStatus);
            }
            if ("4".equals(orderStatus)) {
                if (executeRate == null || "".equals(executeRate)) {
                    throw new ResultException(ResultEnum.http_status_null_message, "执行利率不能为空，请确认后重新请求");
                } else executeRate = CommonMethods.trimAllBlanks(executeRate);
                if (loUsNo == null || "".equals(loUsNo)) {
                    throw new ResultException(ResultEnum.http_status_null_message, "借据编号不能为空，请确认后重新请求");
                } else loUsNo = CommonMethods.trimAllBlanks(loUsNo);
                if (loanMoney == null || "".equals(loanMoney)) {
                    throw new ResultException(ResultEnum.http_status_null_message, "放款金额不能为空，请确认后重新请求");
                } else loanMoney = CommonMethods.trimAllBlanks(loanMoney);
                if (loanStartDate == null || "".equals(loanStartDate)) {
                    throw new ResultException(ResultEnum.http_status_null_message, "放款起始日期不能为空，请确认后重新请求");
                } else loanStartDate = CommonMethods.trimAllBlanks(loanStartDate);
                if (loanEndDate == null || "".equals(loanEndDate)) {
                    throw new ResultException(ResultEnum.http_status_null_message, "放款到期日期不能为空，请确认后重新请求");
                } else loanEndDate = CommonMethods.trimAllBlanks(loanEndDate);

                // 入参类型检查
                try {
                    Double.parseDouble(executeRate);
                    xmlIn.getBody().setEXECUTE_RATE(executeRate);
                } catch (Exception ex) {
                    throw new ResultException(ResultEnum.http_status_not_number, "执行利率格式错误，请输入数字类型");
                }
                if (!CommonMethods.isInteger(loUsNo)){
                    throw new ResultException(ResultEnum.http_status_not_number, "借据编号格式错误，请输入数字类型");
                }
                xmlIn.getBody().setLOUS_NO(loUsNo);
                try {
                    Double.parseDouble(loanMoney);
                    xmlIn.getBody().setLOAN_MONEY(loanMoney);
                } catch (Exception ex) {
                    throw new ResultException(ResultEnum.http_status_not_number, "放款金额格式错误，请输入数字类型");
                }
                if (!CommonMethods.checkIsDate(loanStartDate)) {
                    throw new ResultException(ResultEnum.http_status_date_message, "放款起始日期格式错误，格式为 yyyyMMdd");
                } else xmlIn.getBody().setLOAN_START_DATE(loanStartDate);
                if (!CommonMethods.checkIsDate(loanEndDate)) {
                    throw new ResultException(ResultEnum.http_status_date_message, "放款到期日期格式错误，格式为 yyyyMMdd");
                } else xmlIn.getBody().setLOAN_END_DATE(loanEndDate);

                xmlIn.getBody().setREJECT_REASON(null);
            } else if ("9".equals(orderStatus)) {
                if (orderReason == null || "".equals(orderReason)) {
                    throw new ResultException(ResultEnum.http_status_null_message, "受理拒绝原因不能为空，请确认后重新请求");
                } else xmlIn.getBody().setREJECT_REASON(CommonMethods.trimAllBlanks(orderReason));
            }

            // 入参长度检查
            if (channelCode.length() > 8) {
                throw new ResultException(ResultEnum.http_status_data_too_long, "接入机构号长度过长，请确认后重新请求");
            }
            if (customerCode.length() > 30) {
                throw new ResultException(ResultEnum.http_status_data_too_long, "社会统一信用代码长度过长，请确认后重新请求");
            }
            if (customerName.length() < 2) {
                throw new ResultException(ResultEnum.http_status_data_too_short, "客户名称长度过短，请确认后重新请求");
            }
            if (customerName.length() > 100) {
                throw new ResultException(ResultEnum.http_status_data_too_long, "企业名称长度过长，请确认后重新请求");
            }
            if (orderNo.length() > 30) {
                throw new ResultException(ResultEnum.http_status_data_too_long, "订单编号长度过长，请确认后重新请求");
            }
            if (orderStatus.length() > 2) {
                throw new ResultException(ResultEnum.http_status_data_too_long, "订单状态长度过长，请确认后重新请求");
            }
            if ("9".equals(orderStatus)) {
                if (orderReason.length() < 2) {
                    throw new ResultException(ResultEnum.http_status_data_too_short, "受理拒绝原因长度过短，请确认后重新请求");
                }
                if (orderReason.length() > 200) {
                    throw new ResultException(ResultEnum.http_status_data_too_long, "受理拒绝原因长度过长，请确认后重新请求");
                }
            }
            // 入参关联检查
            // 入参特殊字符
            if (CommonMethods.checkMySqlReservedWords(channelCode))    throw new ResultException(ResultEnum.http_status_data_special,"接入机构号不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(customerCode))    throw new ResultException(ResultEnum.http_status_data_special,"社会统一信用代码不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(customerName))    throw new ResultException(ResultEnum.http_status_data_special,"客户名称不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(orderNo))    throw new ResultException(ResultEnum.http_status_data_special,"订单编号不能有特殊字符，请确认后重新请求");
            /*if ("9".equals(orderStatus)) {
                if (CommonMethods.checkMySqlReservedWords(orderReason)) {
                    throw new ResultException(ResultEnum.http_status_data_special, "受理拒绝原因不能有特殊字符，请确认后重新请求");
                }
            }*/
            logger.info("参数验证完毕，进行后续业务处理");

            // 业务处理
            str = service.loanNotice(xmlIn);
            logger.info("--G373放款通知推送中证--END");
            return str;
        }catch (Exception e){
            logger.info("--G373放款通知推送中证--ERROR");
            e.printStackTrace();
            BodyEntityMSG bodyEntity = new BodyEntityMSG();
            bodyEntity.setRESULT_FLAG("0");
            if (e instanceof NullPointerException || e instanceof JSONException || e instanceof ClassCastException){  /* 请求参数异常 */
                bodyEntity.setMSG(ResultEnum.http_status_request_null.getMsg());
            }else if(e instanceof ResultException){ /* 自定义异常 */
                bodyEntity.setMSG(e.getMessage());
            }else if (e instanceof BadSqlGrammarException){/*  数据库语法错误 */
                bodyEntity.setMSG(ResultEnum.http_status_sql_grammar_mistakes.getMsg());
            }else if (e instanceof CannotCreateTransactionException || e instanceof MyBatisSystemException ){   /* 数据库连接异常*/
                bodyEntity.setMSG(ResultEnum.http_status_sql_connection.getMsg());
            }else if (e instanceof DuplicateKeyException){    /* 数据库主键唯一异常*/
                bodyEntity.setMSG(ResultEnum.http_status_sql_primary.getMsg());
            }else{ /* 其他系统异常 */
                bodyEntity.setMSG(ResultEnum.http_status_internal_server_error.getMsg());
            }
            bodyEntity.setATTRIBUTE6("");
            bodyEntity.setATTRIBUTE7("");
            BodyEntityMSGXMLOut out = new BodyEntityMSGXMLOut();   // 合集出参
            out.setBody(bodyEntity);
            out.setHead(xmlIn.getHead());
            return ResultUtil.getResult(out,BodyEntityMSGXMLOut.class);
        }
    }

    /**
     * G374 推送还款信息
     * 接口功能概述	供应链通过此接口推送借据及还款信息
     * 调用说明	融资客户通过对公网银发起还款，银行成功受理后，供应链通过此接口将借据及还款信息推送给中证。
     */
    public String findPaymentNotice(String req,PaymentNoticeService service){
        logger.info("Controller--G374推送借据及还款信息");
        String str = "";
        PaymentNoticeXMLIn xmlIn = new PaymentNoticeXMLIn();
        try {
            JaxbUtil ju = new JaxbUtil(PaymentNoticeXMLIn.class);
            xmlIn = ju.fromXml(req);

            // 接收参数
            String channelCode = xmlIn.getBody().getCHANNEL_CODE();
            String repayNo = xmlIn.getBody().getREPAY_NO();
            String loanNo = xmlIn.getBody().getLOAN_NO();
            String loanBalance = xmlIn.getBody().getLOAN_BALANCE();
            String repayAmount = xmlIn.getBody().getREPAY_AMOUNT();
            String interestMoney = xmlIn.getBody().getREFUND_INTEREST();
            String repayDatetime = xmlIn.getBody().getREPAY_DATETIME();
            String hqFlag = xmlIn.getBody().getHQ_FLAG();
            String jieJuEndDate = xmlIn.getBody().getJIEJU_END_DATE();
            // 入参检查
            // 入参必须项检查
            if (channelCode == null || "".equals(channelCode)) {
                throw new ResultException(ResultEnum.http_status_null_message, "接入机构号不能为空，请确认后重新请求");
            } else xmlIn.getBody().setCHANNEL_CODE(CommonMethods.trimAllBlanks(channelCode));
            if (repayNo == null || "".equals(repayNo)) {
                throw new ResultException(ResultEnum.http_status_null_message, "还款流水号不能为空，请确认后重新请求");
            } else xmlIn.getBody().setREPAY_NO(CommonMethods.trimAllBlanks(repayNo));
            if (loanNo == null || "".equals(loanNo)) {
                throw new ResultException(ResultEnum.http_status_null_message, "借据号不能为空，请确认后重新请求");
            } else xmlIn.getBody().setLOAN_NO(CommonMethods.trimAllBlanks(loanNo));
            if (loanBalance == null || "".equals(loanBalance)) {
                throw new ResultException(ResultEnum.http_status_null_message, "借据余额不能为空，请确认后重新请求");
            } else loanBalance = CommonMethods.trimAllBlanks(loanBalance);
            if (repayAmount == null || "".equals(repayAmount)) {
                throw new ResultException(ResultEnum.http_status_null_message, "还款本金金额不能为空，请确认后重新请求");
            } else repayAmount = CommonMethods.trimAllBlanks(repayAmount);
        /*if (interestMoney == null || "".equals(interestMoney) ){
            throw new ResultException(ResultEnum.http_status_null_message,"还息金额不能为空，请确认后重新请求");
        }else interestMoney = CommonMethods.trimAllBlanks(interestMoney);*/
            if (repayDatetime == null || "".equals(repayDatetime)) {
                throw new ResultException(ResultEnum.http_status_null_message, "还款时间不能为空，请确认后重新请求");
            } else repayDatetime = CommonMethods.trimAllBlanks(repayDatetime);
            if (hqFlag == null || "".equals(hqFlag)) {
                throw new ResultException(ResultEnum.http_status_null_message, "借据是否结清不能为空，请确认后重新请求");
            } else hqFlag = CommonMethods.trimAllBlanks(hqFlag);
            if (jieJuEndDate == null || "".equals(jieJuEndDate)) {
                throw new ResultException(ResultEnum.http_status_null_message, "借据到期日不能为空，请确认后重新请求");
            } else jieJuEndDate = CommonMethods.trimAllBlanks(jieJuEndDate);
            // 入参类型检查
            try {
                Double.parseDouble(loanBalance);
                xmlIn.getBody().setLOAN_BALANCE(loanBalance);
            } catch (Exception ex) {
                throw new ResultException(ResultEnum.http_status_not_number, "借据余额格式错误，请输入数字类型");
            }
            try {
                Double.parseDouble(repayAmount);
                xmlIn.getBody().setREPAY_AMOUNT(repayAmount);
            } catch (Exception ex) {
                throw new ResultException(ResultEnum.http_status_not_number, "还款本金金额格式错误，请输入数字类型");
            }
            if (interestMoney != null && !"".equals(interestMoney)) {   // 还息金额不为空
                try {
                    Double.parseDouble(interestMoney);
                    xmlIn.getBody().setREFUND_INTEREST(interestMoney);
                } catch (Exception ex) {
                    throw new ResultException(ResultEnum.http_status_not_number, "还息金额格式错误，请输入数字类型");
                }
            }
            if (!CommonMethods.checkIsAllDate(repayDatetime)) {
                throw new ResultException(ResultEnum.http_status_date_message, "还款时间格式错误，格式为 yyyyMMddHHmmss");
            } else xmlIn.getBody().setREPAY_DATETIME(repayDatetime);
            if (!"1".equals(hqFlag) && !"0".equals(hqFlag)) {
                throw new ResultException(ResultEnum.http_status_data_fix, "借据是否结清值必须是‘0’未结清或‘1’已结清 ，请确认后重新请求");
            } else xmlIn.getBody().setHQ_FLAG(hqFlag);
            if (!CommonMethods.checkIsDate(jieJuEndDate)) {
                throw new ResultException(ResultEnum.http_status_date_message, "借据到期日时间格式错误，格式为 yyyyMMdd");
            } else xmlIn.getBody().setJIEJU_END_DATE(jieJuEndDate);
            // 入参长度检查
            if (channelCode.length() > 8) {
                throw new ResultException(ResultEnum.http_status_data_too_long, "接入机构号长度过长，请确认后重新请求");
            }
            if (repayNo.length() > 20) {
                throw new ResultException(ResultEnum.http_status_data_too_long, "还款流水号长度过长，请确认后重新请求");
            }
            if (loanNo.length() > 17) {
                throw new ResultException(ResultEnum.http_status_data_too_long, "借据号长度过长，请确认后重新请求");
            }
            // 入参关联检查
            // 入参特殊字符
            if (CommonMethods.checkMySqlReservedWords(channelCode))    throw new ResultException(ResultEnum.http_status_data_special,"接入机构号不能有特殊字符，请确认后重新请求");
            if (CommonMethods.checkMySqlReservedWords(loanNo))    throw new ResultException(ResultEnum.http_status_data_special,"借据编号不能有特殊字符，请确认后重新请求");
            logger.info("参数验证完毕，进行后续业务处理");
            // 业务处理
            str = service.findPaymentNotice(xmlIn);
            logger.info("--G374推送借据及还款信息--END");
            return str;
        }catch (Exception e){
            logger.info("--G374推送借据及还款信息--ERROR");
            e.printStackTrace();
            BodyEntity bodyEntity = new BodyEntity();
            bodyEntity.setRESULT_FLAG("0");
            if (e instanceof NullPointerException || e instanceof JSONException || e instanceof ClassCastException){  /* 请求参数异常 */
                bodyEntity.setMESSAGE(ResultEnum.http_status_request_null.getMsg());
            }else if(e instanceof ResultException){ /* 自定义异常 */
                bodyEntity.setMESSAGE(e.getMessage());
            }else if (e instanceof BadSqlGrammarException){/*  数据库语法错误 */
                bodyEntity.setMESSAGE(ResultEnum.http_status_sql_grammar_mistakes.getMsg());
            }else if (e instanceof MyBatisSystemException  || e instanceof CannotCreateTransactionException){    /* 数据库连接异常*/
                bodyEntity.setMESSAGE(ResultEnum.http_status_sql_connection.getMsg());
            }else if (e instanceof DuplicateKeyException){    /* 数据库主键唯一异常*/
                bodyEntity.setMESSAGE(ResultEnum.http_status_sql_primary.getMsg());
            }else{ /* 其他系统异常 */
                bodyEntity.setMESSAGE(ResultEnum.http_status_internal_server_error.getMsg());
            }
            bodyEntity.setATTRIBUTE6("");
            bodyEntity.setATTRIBUTE7("");
            BodyEntityXMLOut out = new BodyEntityXMLOut();   // 出参
            out.setBody(bodyEntity);
            out.setHead(xmlIn.getHead());
            return ResultUtil.getResult(out,BodyEntityXMLOut.class);
        }
    }
}