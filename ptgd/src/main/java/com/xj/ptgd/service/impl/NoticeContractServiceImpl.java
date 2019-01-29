package com.xj.ptgd.service.impl;

import com.xj.ptgd.common.exception.ResultException;
import com.xj.ptgd.common.result.ResultEnum;
import com.xj.ptgd.common.result.ResultUtil;
import com.xj.ptgd.common.util.CommonMethods;
import com.xj.ptgd.dao.ContractDao;
import com.xj.ptgd.dao.IntendedDao;
import com.xj.ptgd.dao.ObjectMaxSnDao;
import com.xj.ptgd.entity.body.BodyEntityMSG;
import com.xj.ptgd.entity.body.NoticeCs;
import com.xj.ptgd.entity.body.ObjectMaxSn;
import com.xj.ptgd.entity.in.NoticeContractXMLIn;
import com.xj.ptgd.entity.out.BodyEntityMSGXMLOut;
import com.xj.ptgd.service.NoticeContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.xj.ptgd.common.config.YmlConfig.*;

@Service
@Transactional
public class NoticeContractServiceImpl implements NoticeContractService{
    // 日志输出
    private Logger logger = LoggerFactory.getLogger(NoticeContractService.class);
    @Resource
    ContractDao contractDao;

    @Resource
    IntendedDao intendedDao;

    @Resource
    ObjectMaxSnDao objectMaxSnDao;
    /**
     * 3.15.G392 合同生效通知
     * 接口功能概述	合同生效后，供应链生成PDF文件，通过此接口将文件名称告知中证
     * 调用说明	合同生效后，供应链生成PDF文件，通过此接口将文件名称告知中证
     * @param xml
     * @return
     */
    @Override
    public String noticeContract(NoticeContractXMLIn xml) {
        String type = "";
        String num = "";
        // 根据type code 判断 customer_info 表有没有数据
        if (xml .getBody().getCUSTOMER_CODE().length()>9){ type = "Ent04"; }else type = "Ent01";
        Map<String,Object> customerMap = intendedDao.findCustomerInfo(type,xml.getBody().getCUSTOMER_CODE());
        if(customerMap == null){
            throw new ResultException(ResultEnum.http_status_data_null,"客户社会信用代码不存在，请检查后重新请求");
        }
        String sn = customerMap.get("CUSTOMERID")+"";
        //System.out.println("CUSTOMERID:"+sn);

        if ("".equals(sn)){
            throw new ResultException(ResultEnum.http_status_data_null,"客户社会信用代码不存在，请检查后重新请求");
        }

        Map  map = new HashMap();
        map.put("customerid",sn); // customerid
        map.put("batchnum",xml.getBody().getCONTRACT_BATCH_NUMBER());    // 合同批次号
        Map<String,Object> infoMap = contractDao.findGdContractInfo(map);
        if (infoMap == null){
            throw new ResultException(ResultEnum.http_status_data_null,"合同信息表无数据，请检查后重新请求");
        }
        contractDao.updateGdContractInfoStatus(map);    // 修改 gd_contract_info status = 3

        List<Map<String,Object>> fileList = contractDao.findGdContractFile(map);   // 查询 gd_contract_file
        map.put("channelcode",xml.getBody().getCHANNEL_CODE());
        map.put("customercode",xml.getBody().getCUSTOMER_CODE());
        if (fileList.size() == 0){
            //Map<String,Object> flowMap = contractDao.findGdContractFlow(map);
            map.put("customername",xml.getBody().getCUSTOMER_NAME());
            map.put("oldstatus",infoMap.get("status"));
            map.put("newstatus","3");
            map.put("operationreason","合同生效");
            map.put("operationby","系统");
            String serialNo = getSerialno("GD_CONTRACT_FLOW","SERIALNO","yyyyMMdd");
            map.put("serialno",serialNo);
            contractDao.insertGdContractFlow(map); // 流程表插入

            for (NoticeCs file : xml.getBody().getLIST_OBJ()) {
                String contractno = infoMap.get("contractno").toString();    //最高额保证合同编号
                String agreementnumber = infoMap.get("agreementnumber").toString();    //四方协议编号
                String dcdyhtno = infoMap.get("dcdyhtno").toString();    //动产抵押合同编号
                String creditaugmentedagreementnumber = infoMap.get("creditaugmentedagreementnumber").toString();    //信用增进协议编号
                num = getSerialno("GD_CONTRACT_FILE","SERIALNO","yyyyMMdd");
                map.put("serialno",num);
                map.put("filename",file.getFILE_NAME());
                map.put("filepath",file.getFILE_PATH());
                map.put("contracttype",file.getCONTRACT_TYPE());
                map.put("fileno",null);
                String contractType = file.getCONTRACT_TYPE(); // 获取合同类型
                if(contractType!=null&&!"".equals(contractType)){
                    if(contractType.equals("1")){
                        //最高额保证合同编号
                        map.put("fileno",contractno);
                    } else if(contractType.equals("2")){
                        //四方协议编号
                        map.put("fileno",agreementnumber);
                    } else if(contractType.equals("3")){
                        //动产抵押合同编号
                        map.put("fileno",dcdyhtno);
                    } else if(contractType.equals("4")){
                        //信用增进协议编号
                        map.put("fileno",creditaugmentedagreementnumber);
                    }
                }
                contractDao.insertGdContractFile(map);
            }
        }else {
            throw new ResultException(ResultEnum.http_status_data_have,"已存在合同文件信息，请检查后重新请求");
        }

        logger.info("shell   start");
        for (NoticeCs file : xml.getBody().getLIST_OBJ()) {
            try {
                String cmd = "sh " + SHELL_FILE_NAME + " " + file.getFILE_NAME();
                File dir = new File(SHELL_FILE_URL);
                Process process = Runtime.getRuntime().exec(cmd, null, dir);
                /*int status = process.waitFor();
                 if (status != 0) {
                     if (status == 2) {
                         logger.info("远程文件不存在");
                         //return "2";
                     } else if (status == 3) {
                         logger.info("本地文件下载失败");
                         //return "3";
                     }
                 }*/

                // 设置下载进程的等待时间，实际的下载结果会输出在SecureClient的日志中。 
                boolean processResult = process.waitFor(5, TimeUnit.SECONDS);
                if (processResult) {
                    logger.info("下载完成 - {}", file.getFILE_NAME());
                }
                else{
                    logger.info("超时未完成 - {}", file.getFILE_NAME());
                }
                
                // 输出
                /*BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = "";
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
                input.close();*/
            } catch (Exception e) {
                logger.info("shell 异常");
                //return "error";
            }
        }
        logger.info("shell   end");

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
        ObjectMaxSn entity = objectMaxSnDao.findObjectMax(tableName,columnName,dateFmt);
        if (entity == null){
            throw new ResultException(ResultEnum.http_status_data_null,"流水号数据暂无，请确认后重新请求");
        }
        String serialno = CommonMethods.idPrimaryNextNumber(entity.getMaxSerialNo(),entity.getNoFmt(),entity.getDateFmt());
        if (serialno == null) throw new ResultException(ResultEnum.http_status_internal_server_error,ResultEnum.http_status_internal_server_error.getMsg());
        objectMaxSnDao.updateObjectMaxSnForMaxSerialNo(serialno,entity.getTableName(),entity.getColumnName(),entity.getDateFmt());
        return serialno;
    }
}
