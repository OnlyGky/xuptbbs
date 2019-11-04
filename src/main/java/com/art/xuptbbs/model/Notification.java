package com.art.xuptbbs.model;

import com.art.xuptbbs.model.base.BaseModel;
import com.art.xuptbbs.model.base.Identifiable;
import com.art.xuptbbs.vo.UserVO;

import java.time.LocalDateTime;

public class Notification  extends BaseModel implements Identifiable<Long> {
    private Long id;

    private Long type;

    private Long referenceId;

    private String summary;

    private Long beRead;

    private String fromUserId;

    private String toUserId;

    private User user;

    private LocalDateTime lastModified;

    public Notification() {
    }

    public Notification(Long type, Long referenceId, String summary, String fromUserId, String toUserId, LocalDateTime lastModified) {
        this.type = type;
        this.referenceId = referenceId;
        this.summary = summary;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.lastModified = lastModified;
    }

    @Override
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

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
}
