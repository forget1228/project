package com.xj.ptgd.dao;

import com.xj.ptgd.entity.body.PushContract;

import java.util.List;
import java.util.Map;

public interface ContractDao {
    // gd_contract_info 表
    void insertGdContractInfo(PushContract contract);

    void updateGdContractInfo(PushContract contract);

    void updateGdContractInfoStatus(Map map);

    Map<String,Object> findGdContractInfo(Map map);

    // gd_contract_flow 表
    void insertGdContractFlow(Map map);

    void updateGdContractFlow(Map map);

    Map<String,Object> findGdContractFlow(Map map);

    // gd_contract_file 表
    void insertGdContractFile(Map map);

    List<Map<String,Object>> findGdContractFile(Map map);

    // gd_pttl_object_rule
    Map<String,Object> findGdPttlObjectRule();

    void updateGdPttlObjectRule(String maxno);
}
