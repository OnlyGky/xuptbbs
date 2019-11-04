package com.art.xuptbbs.model;

import com.art.xuptbbs.model.base.BaseModel;
import com.art.xuptbbs.model.base.Identifiable;
import org.springframework.data.mapping.IdentifierAccessor;

import java.time.LocalDateTime;

public class AnswerComment extends BaseModel implements Identifiable<Long> {
    private Long id;

    private String content;

    private Long  parentId;

    private Long postId;

    private Long answerId;

    private String userId;

    private LocalDateTime lastModified;

    private User user;


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

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
