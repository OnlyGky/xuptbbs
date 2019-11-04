package com.art.xuptbbs.model.base;

import java.time.LocalDateTime;

public abstract class BaseModel {
    private LocalDateTime createdAt;
    private long deleted;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public long getDeleted() {
        return deleted;
    }

    public void setDeleted(long deleted) {
        this.deleted = deleted;
    }
}

