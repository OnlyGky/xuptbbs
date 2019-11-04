package com.art.xuptbbs.model;

import com.art.xuptbbs.model.base.BaseModel;
import com.art.xuptbbs.model.base.Identifiable;
import com.art.xuptbbs.vo.UserVO;

import java.time.LocalDateTime;

public class Comment extends BaseModel implements Identifiable<Long> {

    private Long id;

    private String content;

    private Long  parentId;

    private Long postId;

    private String userId;

    private LocalDateTime lastActiveAt;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment() {
    }

    public Comment(Long id, String content, Long parentId, LocalDateTime lastActiveAt) {
        this.id = id;
        this.content = content;
        this.parentId = parentId;
        this.lastActiveAt = lastActiveAt;
    }

    @Override
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getLastActiveAt() {
        return lastActiveAt;
    }

    public void setLastActiveAt(LocalDateTime lastActiveAt) {
        this.lastActiveAt = lastActiveAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", parentId=" + parentId +
                ", postId=" + postId +
                ", userId=" + userId +
                ", lastActiveAt=" + lastActiveAt +
                ", user=" + user +
                '}';
    }
}
