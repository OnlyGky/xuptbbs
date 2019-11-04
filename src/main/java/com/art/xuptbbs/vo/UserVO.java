package com.art.xuptbbs.vo;

import com.art.xuptbbs.model.User;
import org.springframework.beans.BeanUtils;

public class UserVO {
    private String id;
    private String nickname;
    private String avatarUrl;



    public UserVO(User user) {
        BeanUtils.copyProperties(user, this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return "http://yugengkai.top:8080/xuptbbs/images/avatar/"+avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
