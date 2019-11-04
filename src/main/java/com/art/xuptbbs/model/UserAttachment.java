package com.art.xuptbbs.model;

import com.art.xuptbbs.model.base.BaseModel;
import com.art.xuptbbs.model.base.Identifiable;

import java.time.LocalDateTime;

public class UserAttachment extends BaseModel implements Identifiable<Long> {


        private Long id;
        private String fromUserId;
        private String toUserId;

    public UserAttachment(String fromUserId, String toUserId) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public Long getId() {
        return id;
    }
}
