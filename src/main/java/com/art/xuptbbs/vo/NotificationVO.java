package com.art.xuptbbs.vo;


import com.art.xuptbbs.model.Notification;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class NotificationVO {

    public NotificationVO(Notification notification) {
        BeanUtils.copyProperties(notification, this);
        this.userVO = new UserVO(notification.getUser());
    }

    private Long id;

    private Long type;

    private String summary;



    private Long beRead;

    private UserVO userVO;

    private LocalDateTime lastModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    public Long getBeRead() {
        return beRead;
    }

    public void setBeRead(Long beRead) {
        this.beRead = beRead;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
}
