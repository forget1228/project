package com.xiaoji.dao;

import java.util.List;
import java.util.Map;

public interface AbkRecordingMapper {
    void insert(Map map);

    void update(Map map);

    Map findByAbkId(String abk_id);

    Map findByInit(Map map);

    Map findByDocumentName(String documentName);

    List<Map<String,Object>> findGroup(String group_name);
}
