package com.xiaoji.dao;

import java.util.Map;

public interface AbkUCacheMapper {
    void insert(Map map);

    void update(Map map);

    Map find(Map map);
}
