package com.art.xuptbbs.vo;

import com.art.xuptbbs.model.User;
import org.springframework.beans.BeanUtils;

public class UserDetailVO extends UserVO{
    private String major;
    private Long grade;

    public UserDetailVO(User user) {
        super(user);
        BeanUtils.copyProperties(user, this);
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }


}
