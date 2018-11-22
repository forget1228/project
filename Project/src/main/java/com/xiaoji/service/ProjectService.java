package com.xiaoji.service;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    Map abkRecording(Map map);

    Map findAbkRecording(String id);

    List<Map<String,Object>> findAll();

    Map abkUCache(Map map);

    Map findAbkUCache(Map map);
}
