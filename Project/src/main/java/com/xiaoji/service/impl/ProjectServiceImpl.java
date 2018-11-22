package com.xiaoji.service.impl;

import com.xiaoji.dao.AbkRecordingMapper;
import com.xiaoji.dao.AbkUCacheMapper;
import com.xiaoji.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{
    Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Resource
    private AbkRecordingMapper abkRecordingMapper;

    @Resource
    private AbkUCacheMapper abkUCacheMapper;

    @Override
    public Map abkRecording(Map map) {
        Map out = null;
        try {
            Map result = abkUCacheMapper.find(map);
            if (result == null){
                abkRecordingMapper.insert(map);
            }else {
                abkRecordingMapper.update(map);
                out = result;
            }
            return out;
        }catch (Exception e){
            throw new RuntimeException("模板保存失败！",e);
        }
    }

    @Override
    public Map findAbkRecording(String id) { return abkRecordingMapper.findById(id); }

    @Override
    public List<Map<String,Object>> findAll() { return abkRecordingMapper.findAll(); }



    @Override
    public Map abkUCache(Map map) {
        Map out = null;
        try {
            Map result = abkUCacheMapper.find(map);
            if (result == null){
                abkUCacheMapper.insert(map);
            }else {
                abkUCacheMapper.update(map);
                out = result;
            }
            return out;
        }catch (Exception e){
            throw new RuntimeException("缓存保存失败！",e);
        }
    }

    @Override
    public Map findAbkUCache(Map map) { return abkUCacheMapper.find(map); }
}
