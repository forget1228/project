package com.xj.ptgd.dao;

import com.xj.ptgd.entity.body.Dealer;

import java.util.Map;

/**
 * DealerDataDao
 * @author hjd
 * @since  2018/8/2
 */
public interface DealerDataDao {
    /*
    * 插入gd_cust_info表记录
    * */
    void insertGdCustInfo(Dealer dealer);

    /*
    * 查询customer_info表记录
    * */
    Map<String,Object> findCustomerInfo(Map dealer);

    /*
     * 查询gd_cust_info表记录
     * */
    Map<String,Object> findGdCustInfo(Map dealer);

    /*
    * 更新gd_cust_info表记录
    * */
    void updateGdCustInfo(Dealer dealer);

    // gd_pttl_risk_log
    void insertGdPttlRiskLog(Map map);
}
