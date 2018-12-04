package com.xiaoji.service;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    void abkRecording(Map map);

    Map findByAbkId(String abkId);

    List<Map<String,Object>> findAll(String group_name);

    Map abkUCache(Map map);

    Map findAbkUCache(Map map);
}
