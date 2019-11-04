package com.art.xuptbbs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AnswerLikeDTO {

    @NotNull
    private Long answerId;
    @NotBlank
    private String myId;
    @NotBlank
    private String userId;
    @NotNull
    private Integer type;

    private String summary;

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "AnswerLikeDTO{" +
                "AnswerId=" + answerId +
                ", myId='" + myId + '\'' +
                ", userId='" + userId + '\'' +
                ", type=" + type +
                '}';
    }
}
