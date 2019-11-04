package com.art.xuptbbs.vo;

import com.art.xuptbbs.model.AnswerComment;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class AnswerCommentVO {
    private Long id;
    private String content;
    private Long parentId;
    private LocalDateTime lastModified;
    private UserVO userVO;

    public AnswerCommentVO(AnswerComment answerComment) {
        BeanUtils.copyProperties(answerComment, this);
        userVO = new UserVO(answerComment.getUser());
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

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

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
}
