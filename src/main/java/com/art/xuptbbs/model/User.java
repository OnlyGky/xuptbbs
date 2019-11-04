package com.art.xuptbbs.model;

import com.art.xuptbbs.model.base.BaseModel;
import com.art.xuptbbs.model.base.Identifiable;

import java.time.LocalDateTime;

public class User extends BaseModel implements Identifiable<String> {

    private String id;
    private String nickname;
    private String avatarUrl;
    private String major;
    private Long grade;
    private LocalDateTime lastLoginAt;


    @Override
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
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public User(String id, String nickname, String avatarUrl, LocalDateTime lastLoginAt, LocalDateTime createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
        this.lastLoginAt = lastLoginAt;
        super.setCreatedAt(createdAt);
    }

    public User() {
    }
}
