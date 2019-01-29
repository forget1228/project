package com.xj.ptgd.entity.body;

import com.xj.ptgd.entity.base.BaseDto;


/**
  * User 
  * @author wkm
  * @since 2018/7/30
  */
public class User extends BaseDto {
    private Integer userId;
    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
