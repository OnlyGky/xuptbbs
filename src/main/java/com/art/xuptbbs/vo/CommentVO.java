package com.art.xuptbbs.vo;

import java.time.LocalDateTime;

public class CommentVO {
    private Long id;
    private String content;
    private Long parentId;
    private LocalDateTime lastActiveAt;

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

    public LocalDateTime getLastActiveAt() {
        return lastActiveAt;
    }

    public void setLastActiveAt(LocalDateTime lastActiveAt) {
        this.lastActiveAt = lastActiveAt;
    }

    public CommentVO(Long id, String content, Long parentId, LocalDateTime lastActiveAt) {
        this.id = id;
        this.content = content;
        this.parentId = parentId;
        this.lastActiveAt = lastActiveAt;
    }
}
