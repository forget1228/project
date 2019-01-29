package com.xiaoji.service;

import com.xiaoji.model.BbgTax;
import com.xiaoji.model.BbgWage;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    List<Map<String,Object>> findBbgByDate(String date);
    String insertBbg(List<BbgWage> wageList, List<BbgTax> taxList, String time, String version);
}
