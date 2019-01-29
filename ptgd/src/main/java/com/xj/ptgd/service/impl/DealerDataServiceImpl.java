package com.xj.ptgd.service.impl;

import com.xj.ptgd.common.exception.ResultException;
import com.xj.ptgd.common.httpXml.HttpsUtils;
import com.xj.ptgd.common.result.ResultEnum;
import com.xj.ptgd.common.result.ResultUtil;
import com.xj.ptgd.common.util.CommonMethods;
import com.xj.ptgd.dao.DealerDataDao;
import com.xj.ptgd.dao.IntendedDao;
import com.xj.ptgd.dao.ObjectMaxSnDao;
import com.xj.ptgd.entity.body.BodyEntity;
import com.xj.ptgd.entity.body.BodyEntityMSG;
import com.xj.ptgd.entity.body.ObjectMaxSn;
import com.xj.ptgd.entity.in.DealerXMLIn;
import com.xj.ptgd.entity.out.BodyEntityMSGXMLOut;
import com.xj.ptgd.entity.out.BodyEntityXMLOut;
import com.xj.ptgd.service.DealerDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.xj.ptgd.common.config.YmlConfig.RAIN_SCORE_URL;
import static com.xj.ptgd.common.config.YmlConfig.UNDER_TAKER_URL;

/**
 * DealerDataServiceImpl
 * @since 2018/8/2
 */
@Service
public class DealerDataServiceImpl implements DealerDataService {
    @Resource
    private DealerDataDao dealerDataDao;

    @Resource
    ObjectMaxSnDao objectMaxSnDao;

    @Resource
    IntendedDao intendedDao;
    /**
     * G371 推送经销商数据清单
     * 接口功能概述	供应链通过此接口推送经销商数据清单
     * 调用说明	供应链通过此接口将经销商数据清单信息推送给中证。
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String pushDealerData(DealerXMLIn xmlIn) {
        String type = "";
        String channel = xmlIn.getBody().getCHANNEL_CODE(); //接入机构号
        if (xmlIn.getBody().getCUSTOMER_CODE().length()>9){ type = "Ent04"; }else type = "Ent01";

        Map map = new HashMap();
        map.put("CERTTYPE",type);
        map.put("CUSTOMER_CODE",xmlIn.getBody().getCUSTOMER_CODE());
        map.put("CUST_NO",xmlIn.getBody().getCUST_NO());
        Map<String,Object> mc = dealerDataDao.findCustomerInfo(map);

        /* 查询 */
        Map<String,Object> mg = dealerDataDao.findGdCustInfo(map);
        if(mc != null){ //判断统一社会代码是否存在 存在更新，不存在插入
            xmlIn.getBody().setCUSTOMER_ID(mc.get("CUSTOMERID")+"");
            if(mg!=null){ //判断是否更新光大普天经销商信息表
                dealerDataDao.updateGdCustInfo(xmlIn.getBody());
            }else{
                dealerDataDao.insertGdCustInfo(xmlIn.getBody());
            }
            intendedDao.updateCustomerInfo(xmlIn.getBody().getCUST_NAME(),mc.get("CUSTOMERID")+"",type,xmlIn.getBody().getCUSTOMER_CODE());
            intendedDao.updateEntInfo(xmlIn.getBody().getCUST_NAME(),mc.get("CUSTOMERID")+"",xmlIn.getBody().getCUSTOMER_CODE());
        }else{
            // 依据规则生成 ID
            String customerId = getIdPrimary("CUSTOMER_INFO","CUSTOMERID","yyyyMMdd");
            intendedDao.addCustomerInfo(customerId,xmlIn.getBody().getCUST_NAME(), "0120",type,xmlIn.getBody().getCUSTOMER_CODE(),channel);
            intendedDao.addEntInfo(xmlIn.getBody().getCUSTOMER_CODE(),xmlIn.getBody().getCUST_NAME(),customerId);
            xmlIn.getBody().setCUSTOMER_ID(customerId);
//            dealerDataDao.insertGdCustInfo(xmlIn.getBody());
            if(mg!=null){
                dealerDataDao.updateGdCustInfo(xmlIn.getBody());
            }else{
                dealerDataDao.insertGdCustInfo(xmlIn.getBody());
            }
        }

        String ruleIdCustomerType = "G371001";
        String ruleIdOwnerPhone = "G371002";
        String ruleIdCustomerCode = "G371003";
        String number = null;
        String pass = "1";
        String fail = "2";
        map.put("customerid",xmlIn.getBody().getCUSTOMER_ID());
        map.put("customername", xmlIn.getBody().getCUST_NAME());
        map.put("operationby", "系统");
        ObjectMaxSn objectMaxSn = objectMaxSnDao.findObjectMax("GD_PTTL_RISK_LOG", "SERIALNO", "yyyyMMdd");
        if (objectMaxSn == null) {
            throw new ResultException(ResultEnum.http_status_data_null, "光大普天风控日志表流水号数据暂无，请联系管理员");
        }
        try {
            number = CommonMethods.idPrimaryNextNumber(objectMaxSn.getMaxSerialNo(), objectMaxSn.getNoFmt(), objectMaxSn.getDateFmt());

            // customer_type
            String customer_type = xmlIn.getBody().getCUSTOMER_TYPE();
            map.put("serialno", number);
            map.put("ruleid", ruleIdCustomerType);
            if (customer_type.indexOf("TOP1000客户") != -1 || customer_type.indexOf("368客户") != -1) {
                map.put("ruleresult", pass);
                map.put("reason", "pass  客户分类：" + customer_type);
                dealerDataDao.insertGdPttlRiskLog(map);
            } else {
                map.put("ruleresult", fail);
                map.put("reason", "不通过  客户分类：" + customer_type);
                dealerDataDao.insertGdPttlRiskLog(map);
                return ruleIdCustomerType;
            }

            // owner_phoneno
            /*String owner_phoneno = xmlIn.getBody().getOWNER_PHONENO();
            Map<String, String> params = new HashMap<>();
            //系统参数
            params.put("reqId", "25dcf7db266c249a9b161111");
            params.put("merchantId", "111111");//合作机构
            params.put("token", "123456789");//token
            //业务参数
            params.put("mobile", owner_phoneno); //手机号
            try {
                number = CommonMethods.idPrimaryNextNumber(number, objectMaxSn.getNoFmt(), objectMaxSn.getDateFmt());
                map.put("serialno", number);
                map.put("ruleid", ruleIdOwnerPhone);
                String scope = HttpsUtils.doHttpPostAction(params, RAIN_SCORE_URL);
                if (Integer.parseInt(scope) < 6) {
                    map.put("ruleresult", pass);
                    map.put("reason", "pass  手机号码：" + owner_phoneno);
                }else {
                    map.put("ruleresult", fail);
                    map.put("reason", "不通过  手机号码：" + owner_phoneno);
                    return ruleIdOwnerPhone;
                }
            }catch (Exception e){
                map.put("ruleresult", fail);
                map.put("reason", "不通过  手机号码：" + owner_phoneno);
                return "fail";
            }finally {
                dealerDataDao.insertGdPttlRiskLog(map);
            }*/

            // customer_code
            /*String customer_code = xmlIn.getBody().getCUSTOMER_CODE();
            Map<String, String> codeMap = new HashMap<>();
            //系统参数
            codeMap.put("reqId", "25dcf7db266c249a9b161111");
            codeMap.put("merchantId", "111111");// 合作机构
            codeMap.put("token", "123456789");//token
            //业务参数
            codeMap.put("queryNo", customer_code); // 组织机构代码/个人身份证号码
            try {
                number = CommonMethods.idPrimaryNextNumber(number, objectMaxSn.getNoFmt(), objectMaxSn.getDateFmt());
                map.put("serialno", number);
                map.put("ruleid", ruleIdCustomerCode);
                String result = HttpsUtils.doHttpPostAction(codeMap, UNDER_TAKER_URL);
                if ("无记录".equals(result)) {
                    map.put("ruleresult", pass);
                    map.put("reason", "pass  统一社会信用代码：" + customer_code);
                }else {
                    map.put("ruleresult", fail);
                    map.put("reason", "不通过  统一社会信用代码：" + customer_code);
                    return ruleIdCustomerCode;
                }
            }catch (Exception e){
                map.put("ruleresult", fail);
                map.put("reason", "不通过  统一社会信用代码：" + customer_code);
                return "fail";
            }finally {
                dealerDataDao.insertGdPttlRiskLog(map);
            }*/
            // 风险end

        }catch (Exception e){
            return "fail";
        }finally {
            objectMaxSnDao.updateObjectMaxSnForMaxSerialNo(number, objectMaxSn.getTableName(), objectMaxSn.getColumnName(), objectMaxSn.getDateFmt());
        }
        BodyEntityMSG bodyEntity = new BodyEntityMSG();
        bodyEntity.setRESULT_FLAG("1");
        bodyEntity.setMSG("");
        bodyEntity.setATTRIBUTE6("");
        bodyEntity.setATTRIBUTE7("");
        BodyEntityMSGXMLOut out = new BodyEntityMSGXMLOut();   // 合集出参
        out.setBody(bodyEntity);
        out.setHead(xmlIn.getHead());
        return ResultUtil.getResult(out,BodyEntityMSGXMLOut.class);
    }

    private String getIdPrimary(String tableName,String columnName,String dateFmt) {
        ObjectMaxSn entity = objectMaxSnDao.findObjectMax(tableName,columnName,dateFmt);
        //System.out.println(entity);
        if (entity == null){
            throw new ResultException(ResultEnum.http_status_data_null,"流水号数据暂无，请确认后重新请求");
        }
        // 生成序列号
        String id = CommonMethods.idPrimaryNextNumber(entity.getMaxSerialNo(),entity.getNoFmt(),entity.getDateFmt());
        if (id == null) throw new ResultException(ResultEnum.http_status_internal_server_error,ResultEnum.http_status_internal_server_error.getMsg());
        // 修改最大值
        objectMaxSnDao.updateObjectMaxSnForMaxSerialNo(id,entity.getTableName(),entity.getColumnName(),entity.getDateFmt());
        return id;
    }
}
