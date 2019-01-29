package com.xj.ptgd.entity.body;

import com.xj.ptgd.entity.base.BaseDto;

import java.util.List;

public class UserList extends BaseDto {
    List<User> Frame;

    public List<User> getFrame() {
        return Frame;
    }

    public void setFrame(List<User> frame) {
        Frame = frame;
    }
}
