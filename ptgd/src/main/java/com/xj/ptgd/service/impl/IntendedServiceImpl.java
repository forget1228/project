package com.xj.ptgd.service.impl;

import com.xj.ptgd.common.exception.ResultException;
import com.xj.ptgd.common.result.ResultEnum;
import com.xj.ptgd.common.result.ResultUtil;
import com.xj.ptgd.common.util.CommonMethods;
import com.xj.ptgd.dao.IntendedDao;
import com.xj.ptgd.dao.LoanLimitDao;
import com.xj.ptgd.dao.ObjectMaxSnDao;
import com.xj.ptgd.entity.body.BodyEntity;
import com.xj.ptgd.entity.body.Intention;
import com.xj.ptgd.entity.body.ObjectMaxSn;
import com.xj.ptgd.entity.in.IntentionXmlIn;
import com.xj.ptgd.entity.out.BodyEntityXMLOut;
import com.xj.ptgd.service.IntendedService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * G348 推送客户额度信息
 * 供应链手动将额度信息推送给中证，只在批复生效，额度启用后，由业务人员手工触发推送客户
 * @author cp
 * @date 2018/8/2
 */
@Service
public class IntendedServiceImpl implements IntendedService {
    @Resource
    IntendedDao intendedDao;

    @Resource
    LoanLimitDao loanLimitDao;

    @Resource
    ObjectMaxSnDao objectMaxSnDao;
    /**
     *  G348 推送客户额度信息
     * 供应链手动将额度信息推送给中证，只在批复生效，额度启用后，由业务人员手工触发推送客户
     * @param xml IntentionXmlIn  Intention
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String save(IntentionXmlIn xml) {
        String type = "";
        String sn = null;
        Intention in = xml.getBody();
        if (xml .getBody().getCUSTOMER_CODE().length()>9){ type = "Ent04"; }else type = "Ent01";
        Map<String,Object> map = intendedDao.findCustomerInfo(type,in.getCUSTOMER_CODE());
        if(map == null){
            throw new ResultException(ResultEnum.http_status_data_null,"客户社会信用代码不存在，请检查后重新请求");
        }
        sn = map.get("CUSTOMERID")+"";
        //System.out.println("sn:"+sn);

        if ( sn !=null ){
            intendedDao.updateCustomerInfo(in.getCUSTOMER_NAME(),sn,type,in.getCUSTOMER_CODE());
            if (intendedDao.findEntCustomerId(sn)!=null) {
                intendedDao.updateEntInfo(in.getCUSTOMER_NAME(), sn, in.getCUSTOMER_CODE());
            }else {
                intendedDao.addEntInfo(in.getCUSTOMER_CODE(),in.getCUSTOMER_NAME(),sn);
            }
            if (loanLimitDao.findLoanLimitCustomerId(sn)!=null) {
                loanLimitDao.updateLoanLimit(in.getTOTAL_LIMIT(),in.getAVAILABLE_LIMIT(),
                        in.getLIMIT_BEGIN_DATE(),in.getLIMIT_END_DATE(),sn,in.getCUSTOMER_NAME(),
                        in.getCHANNEL_CODE());
            }else {
                String loanId = getIdPrimary("LOAN_LIMIT","SERIALNO","yyyyMMdd");
                loanLimitDao.addLoanLimit(in.getTOTAL_LIMIT(),in.getLIMIT_BEGIN_DATE(),in.getLIMIT_END_DATE(),xml .getBody().getCREDITLINE_STATE(),loanId,sn,in.getCUSTOMER_NAME(),in.getAVAILABLE_LIMIT(),in.getCHANNEL_CODE());
            }
        }else   throw new ResultException(ResultEnum.http_status_data_null,"客户社会信用代码不存在，请检查后重新请求");

        BodyEntityXMLOut out = new BodyEntityXMLOut();   // 合集出参
        BodyEntity bodyEntity = new BodyEntity();   // 出参定义 Body
        bodyEntity.setRESULT_FLAG("1");
        bodyEntity.setMESSAGE("");
        bodyEntity.setATTRIBUTE6("");
        bodyEntity.setATTRIBUTE7("");
        out.setHead(xml.getHead());
        out.setBody(bodyEntity);
        // 转换成xml格式
        return ResultUtil.getResult(out,BodyEntityXMLOut.class);
    }

    private String getIdPrimary(String tableName, String columnName, String dateFmt) {
        ObjectMaxSn entity = objectMaxSnDao.findObjectMax(tableName,columnName,dateFmt);
        //System.out.println(entity);
        if (entity == null){
            throw new ResultException(ResultEnum.http_status_data_null,"流水号数据暂无，请确认后重新请求");
        }
        // 生成序列号
        CommonMethods commonMethods = new CommonMethods();
        String id = commonMethods.idPrimaryNextNumber(entity.getMaxSerialNo(),entity.getNoFmt(),entity.getDateFmt());
        if (id == null) throw new ResultException(ResultEnum.http_status_internal_server_error,ResultEnum.http_status_internal_server_error.getMsg());
        // 修改最大值
        objectMaxSnDao.updateObjectMaxSnForMaxSerialNo(id,entity.getTableName(),entity.getColumnName(),entity.getDateFmt());
        return id;
    }
}
