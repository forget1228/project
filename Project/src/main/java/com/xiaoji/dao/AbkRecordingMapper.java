package com.xiaoji.dao;

import java.util.List;
import java.util.Map;

public interface AbkRecordingMapper {
    void insert(Map map);

    void update(Map map);

    Map findById(String id);

    List<Map<String,Object>> findAll();
}
