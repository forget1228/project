package com.xj.ptgd.service.impl;

import com.xj.ptgd.common.exception.ResultException;
import com.xj.ptgd.common.result.ResultEnum;
import com.xj.ptgd.common.result.ResultUtil;
import com.xj.ptgd.dao.OrderDao;
import com.xj.ptgd.entity.body.BodyOrderCancel;
import com.xj.ptgd.entity.in.OrderCancelXMLIn;
import com.xj.ptgd.entity.out.BodyOrderCancelXMLOut;
import com.xj.ptgd.service.OrderCancelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 *  G362 订单取消通知－中证开发
 *  光大银行将取消的订单信息通过此接口通知中证
 * @author cp
 * @date 2018/8/2
 */
@Service
public class OrderCancelServiceImpl implements OrderCancelService {
    @Resource
    OrderDao orderDao;

    /**
     *  G362 订单取消通知－中证开发
     *  光大银行将取消的订单信息通过此接口通知中证
     * @param xml   OrderCancelXMLIn OrderCancel
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String save(OrderCancelXMLIn xml){
        Map<String,Object> mc = orderDao.findGdOrderInfoByThree(xml.getBody().getORDER_NO(),xml.getBody().getCUSTOMER_CODE(),xml.getBody().getCUSTOMER_NAME());
        if( mc != null){
            orderDao.updateGdStatus(xml.getBody().getCUSTOMER_CODE(),xml.getBody().getORDER_NO(),"6",null,xml.getBody().getCHANNEL_CODE());
        }else   throw new ResultException(ResultEnum.http_status_data_null,"该请求参数查询无数据，请确认后重新请求");

        BodyOrderCancelXMLOut out = new BodyOrderCancelXMLOut();   // 合集出参
        BodyOrderCancel bodyEntity = new BodyOrderCancel();   // 出参定义 Body
        bodyEntity.setRESULT_FLAG("1");
        bodyEntity.setMSG("");
        bodyEntity.setATTRIBUTE6("");
        bodyEntity.setATTRIBUTE7("");
        bodyEntity.setATTRIBUTE8("");
        bodyEntity.setATTRIBUTE9("");
        bodyEntity.setATTRIBUTE10("");
        out.setHead(xml.getHead());
        out.setBody(bodyEntity);
        // 转换成xml格式
        return ResultUtil.getResult(out,BodyOrderCancelXMLOut.class);
    }
}