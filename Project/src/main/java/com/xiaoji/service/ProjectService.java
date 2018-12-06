package com.xiaoji.service;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    void insertAbkRecording(Map map);

    void updateAbkRecording(Map map);

    Map findByAbkId(String abkId);

    Map findByInit(Map map);

    Map findByDocumentName(String documentName);

    List<Map<String,Object>> findAll(String group_name);

    Map abkUCache(Map map);

    Map findAbkUCache(Map map);
}
