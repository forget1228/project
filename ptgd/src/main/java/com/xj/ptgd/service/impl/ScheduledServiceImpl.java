package com.xj.ptgd.service.impl;

import com.xj.ptgd.common.result.ResultUtil;
import com.xj.ptgd.entity.base.XMLHeadDto;
import com.xj.ptgd.entity.body.UserList;
import com.xj.ptgd.entity.out.UserListXMlOut;
import com.xj.ptgd.service.ScheduleService;
import org.springframework.stereotype.Service;

@Service(value = "scheduledService")
public class ScheduledServiceImpl implements ScheduleService {
//    @Autowired
//    private UserDao userDao;

    public String findUsers(){
        UserListXMlOut ret = new UserListXMlOut();
        XMLHeadDto head = new XMLHeadDto();
        UserList userList = new UserList();
//        userList.setFrame(userDao.findUsers());
        ret.setBody(userList);
        return ResultUtil.setHeadAndGetResult(head,ret,UserListXMlOut.class);
    }
}
