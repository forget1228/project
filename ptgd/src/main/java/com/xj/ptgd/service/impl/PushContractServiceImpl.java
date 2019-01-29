package com.xj.ptgd.service.impl;

import com.xj.ptgd.common.exception.ResultException;
import com.xj.ptgd.common.result.ResultEnum;
import com.xj.ptgd.common.result.ResultUtil;
import com.xj.ptgd.common.util.CommonMethods;
import com.xj.ptgd.dao.ContractDao;
import com.xj.ptgd.dao.IntendedDao;
import com.xj.ptgd.dao.ObjectMaxSnDao;
import com.xj.ptgd.entity.body.BodyEntityMSG;
import com.xj.ptgd.entity.body.ObjectMaxSn;
import com.xj.ptgd.entity.body.PushContract;
import com.xj.ptgd.entity.in.PushContractXMLIn;
import com.xj.ptgd.entity.out.BodyEntityMSGXMLOut;
import com.xj.ptgd.service.PushContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 3.13.G390推送合同信息
 * 接口功能概述	供应链通过此接口接收中证推送《动产抵押合同》、《最高额保证合同》《信用增进服务协议》、《四方合作协议》信息字段
 * 调用说明	小微客户经理维护合同后 ，将通过此接口接收中证推送《动产抵押合同》、《最高额保证合同》《信用增进服务协议》、《四方合作协议》信息字段
 */
@Service
@Transactional
public class PushContractServiceImpl implements PushContractService {

    @Resource
    ContractDao contractDao;

    @Resource
    IntendedDao intendedDao;

    @Resource
    ObjectMaxSnDao objectMaxSnDao;

    @Override
    public String pushContract(PushContractXMLIn xml) {
        PushContract body = xml.getBody();
        String type = "";
        // 根据type code 判断 customer_info 表有没有数据
        if (xml .getBody().getCUSTOMER_CODE().length()>9){ type = "Ent04"; }else type = "Ent01";
        Map<String,Object> customerMap = intendedDao.findCustomerInfo(type,body.getCUSTOMER_CODE());
        if(customerMap == null){
            throw new ResultException(ResultEnum.http_status_data_null,"客户社会信用代码不存在，请检查后重新请求");
        }
        String sn = customerMap.get("CUSTOMERID")+"";
        //System.out.println("CUSTOMERID:"+sn);
        body.setCUSTOMER_ID(sn);

        Map  map = new HashMap();
        map.put("customerid",sn); // customerid
        map.put("batchnum",body.getCONTRACT_BATCH_NUMBER());    // 合同批次号

        if (!"".equals(sn)){
            if ("1".equals(body.getOPERATION_TYPE())){  // 1-新增
                String maxno = null;
                Map<String,Object> gdRuleMap = contractDao.findGdPttlObjectRule();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    sb.append("0");
                }
                DecimalFormat df = new DecimalFormat(sb.toString());
                maxno = df.format( 1 + Integer.parseInt(gdRuleMap.get("maxno").toString()));
                contractDao.updateGdPttlObjectRule(maxno);
                body.setFDBBZHT_NO(gdRuleMap.get("objectno")+"-"+maxno+"-"+"FDB");
                body.setDCDYHT_NO(gdRuleMap.get("objectno")+"-"+maxno+"-"+"DCDY");
                body.setCREDIT_AUGMENTED_AGREEMENT_NUMBER(gdRuleMap.get("objectno")+"-"+maxno+"-"+"XYZJ");
                contractDao.insertGdContractInfo(body);

                map.put("customername",body.getCUSTOMER_NAME());
                map.put("operationreason","信息插入");
                map.put("operationby","系统");
                map.put("oldstatus","0");
                map.put("newstatus","0");
                String serialNo = getSerialno("GD_CONTRACT_FLOW","SERIALNO","yyyyMMdd");
                map.put("serialno",serialNo);
                contractDao.insertGdContractFlow(map);  // 流程表插入
            }else if ("2".equals(body.getOPERATION_TYPE())){  // 2-修改
                Map<String,Object> infoMap = contractDao.findGdContractInfo(map);
                if (infoMap == null){
                    throw new ResultException(ResultEnum.http_status_data_null,"请先进行新增操作");
                }
                if ("3".equals(infoMap.get("status"))){
                    throw new ResultException(ResultEnum.http_status_data_null,"合同状态已生效，请检查后重新请求");
                }

                //Map<String,Object> flowMap = contractDao.findGdContractFlow(map);
                map.put("customername",body.getCUSTOMER_NAME());
                map.put("operationreason","信息变更");
                map.put("operationby","系统");
                map.put("oldstatus",infoMap.get("status"));
                map.put("newstatus","0");
                String serialNo = getSerialno("GD_CONTRACT_FLOW","SERIALNO","yyyyMMdd");
                map.put("serialno",serialNo);
                contractDao.insertGdContractFlow(map);  // 流程表插入

                contractDao.updateGdContractInfo(body); // Info表插入
            }
        }else throw new ResultException(ResultEnum.http_status_data_null,"客户社会信用代码不存在，请检查后重新请求");

        BodyEntityMSG bodyEntity = new BodyEntityMSG();
        bodyEntity.setRESULT_FLAG("1");
        bodyEntity.setMSG("");
        bodyEntity.setATTRIBUTE6("");
        bodyEntity.setATTRIBUTE7("");
        BodyEntityMSGXMLOut out = new BodyEntityMSGXMLOut();   // 合集出参
        out.setBody(bodyEntity);
        out.setHead(xml.getHead());
        return ResultUtil.getResult(out,BodyEntityMSGXMLOut.class);
    }

    private String getSerialno(String tableName, String columnName, String dateFmt) {
        CommonMethods commonMethods = new CommonMethods();
        ObjectMaxSn entity = objectMaxSnDao.findObjectMax(tableName,columnName,dateFmt);
        if (entity == null){
            throw new ResultException(ResultEnum.http_status_data_null,"流水号数据暂无，请确认后重新请求");
        }
        String serialno = commonMethods.idPrimaryNextNumber(entity.getMaxSerialNo(),entity.getNoFmt(),entity.getDateFmt());
        if (serialno == null) {
            throw new ResultException(ResultEnum.http_status_internal_server_error, ResultEnum.http_status_internal_server_error.getMsg());
        }
        objectMaxSnDao.updateObjectMaxSnForMaxSerialNo(serialno,entity.getTableName(),entity.getColumnName(),entity.getDateFmt());
        return serialno;
    }
}
