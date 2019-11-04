package com.art.xuptbbs.vo;

import com.art.xuptbbs.model.User;

public class UserAllDetailVO extends UserDetailVO{
    private Long follow;
    private Long fans;
    private String email;
    public UserAllDetailVO(User user) {
        super(user);
    }


    public Long getFollow() {
        return follow;
    }

    public void setFollow(Long follow) {
        this.follow = follow;
    }

    public Long getFans() {
        return fans;
    }

    public void setFans(Long fans) {
        this.fans = fans;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
