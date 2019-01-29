package com.xiaoji.dao;

import com.xiaoji.model.BbgTax;
import com.xiaoji.model.BbgWage;

import java.util.List;
import java.util.Map;

public interface BbgMapper {
    List<Map<String,Object>> findBbgByDate(String wage_date);
    Map<String,Object> findSUMBbgByDate(String wage_date);

    // bbg_wage
    void insertBbgWage(BbgWage bbgWage);
    void deleteBbgWageByWageDate(String wage_date);

    // bbg_tax
    void insertBbgTax(BbgTax bbgTax);
    void deleteBbgWageByTaxDate(String tax_date);
}
