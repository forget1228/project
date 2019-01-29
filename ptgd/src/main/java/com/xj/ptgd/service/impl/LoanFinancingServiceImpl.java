package com.xj.ptgd.service.impl;

import com.xj.ptgd.common.exception.ResultException;
import com.xj.ptgd.common.result.ResultEnum;
import com.xj.ptgd.common.result.ResultUtil;
import com.xj.ptgd.dao.OrderDao;
import com.xj.ptgd.entity.body.BodyEntity;
import com.xj.ptgd.entity.body.BodyEntityMSG;
import com.xj.ptgd.entity.body.LoanFinancing;
import com.xj.ptgd.entity.in.LoanFinancingXMLIn;
import com.xj.ptgd.entity.out.BodyEntityMSGXMLOut;
import com.xj.ptgd.entity.out.BodyEntityXMLOut;
import com.xj.ptgd.service.LoanFinancingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 *  G353 订单融资申请转发中证
 *  供应链订单融资信息转发至中证
 * @author cp
 * @date 2018/8/2
 */
@Service
public class LoanFinancingServiceImpl implements LoanFinancingService {
    @Resource
    OrderDao orderDao;

    /**
     * G353 订单融资申请转发中证
     * @param xml   LoanFinancingXMLIn  LoanFinancing
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String save(LoanFinancingXMLIn xml) {
        String type = "";
        LoanFinancing in = xml.getBody();
        if (xml .getBody().getCUSTOMER_CODE().length()>9){ type = "Ent04"; }else type = "Ent01";
        String sn = orderDao.findCustomerInfoForCustomerId(type,in.getCUSTOMER_CODE());
        //System.out.println("sn:"+sn);
        if (sn != null) {
            Map<String, Object> mc = orderDao.findGdOrderInfo(in.getORDER_NO(), in.getCUSTOMER_CODE());
            if (orderDao.findGdOrderNo(in.getORDER_NO()) != null){
                throw new ResultException(ResultEnum.http_status_data_have, "该订单编号已存在，请确认后重新请求");
            }
            if (mc != null) {
                orderDao.updateGdOrderInfo(in.getCUSTOMER_NAME(), in.getORDER_AMOUNT(), in.getORDER_DATE(),
                        in.getSELF_PAY(), in.getSELF_PAY_RATE(), in.getBRACH_COMPANY(),
                        in.getBRACH_ACCOUNT(), in.getCUSTOMER_CODE(), in.getORDER_NO());
            } else {
                orderDao.addGdOrderInfo(in.getCUSTOMER_CODE(), in.getCUSTOMER_NAME(), in.getORDER_NO(),
                        Double.parseDouble(in.getORDER_AMOUNT()), in.getORDER_DATE(),
                        Double.parseDouble(in.getSELF_PAY()), in.getSELF_PAY_RATE(),
                        in.getBRACH_COMPANY(), in.getBRACH_ACCOUNT(), sn,in.getCHANNEL_CODE());
            }
        }else   throw new ResultException(ResultEnum.http_status_data_null, "该订单编号和客户社会统一信用代码无数据，请确认后重新请求");

/*        BodyEntityXMLOut out = new BodyEntityXMLOut();   // 合集出参
        BodyEntity bodyEntity = new BodyEntity();   // 出参定义 Body
        bodyEntity.setRESULT_FLAG("1");
        bodyEntity.setMESSAGE("");
        bodyEntity.setATTRIBUTE6("");
        bodyEntity.setATTRIBUTE7("");
        out.setHead(xml.getHead());
        out.setBody(bodyEntity);
        // 转换成xml格式
        return ResultUtil.getResult(out,BodyEntityXMLOut.class);*/

        BodyEntityMSG bodyEntity = new BodyEntityMSG();
        bodyEntity.setMSG("");
        bodyEntity.setRESULT_FLAG("1");
        bodyEntity.setATTRIBUTE6("");
        bodyEntity.setATTRIBUTE7("");
        BodyEntityMSGXMLOut out = new BodyEntityMSGXMLOut();   // 合集出参
        out.setBody(bodyEntity);
        out.setHead(xml.getHead());
        return ResultUtil.getResult(out,BodyEntityMSGXMLOut.class);


    }
}
