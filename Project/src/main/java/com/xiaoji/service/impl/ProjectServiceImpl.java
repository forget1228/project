package com.xiaoji.service.impl;

import com.xiaoji.dao.AbkRecordingMapper;
import com.xiaoji.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{
    Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Resource
    private AbkRecordingMapper abkRecordingMapper;

    @Override
    public void insert(Map map) {
        abkRecordingMapper.insert(map);
    }

    @Override
    public void update(Map map) {
        abkRecordingMapper.update(map);
    }
}
