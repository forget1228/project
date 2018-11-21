package com.xiaoji.service;

import java.util.Map;

public interface ProjectService {
    Map abkRecording(Map map);

    Map findAbkRecording(String id);

    Map abkUCache(Map map);

    Map findAbkUCache(Map map);
}
