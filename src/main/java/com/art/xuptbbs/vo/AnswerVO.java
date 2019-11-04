package com.art.xuptbbs.vo;

import com.art.xuptbbs.model.Answer;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

public class AnswerVO {

    public AnswerVO(Answer answer){
        BeanUtils.copyProperties(answer, this);
        userVO = new UserVO(answer.getUser());
    }


    private Long id;

    private String content;

    private Long likeCount;

    private Long commentCount;

    private LocalDateTime lastModified;

    private int isLike;

    private UserVO userVO;

    private List<AnswerCommentVO> commentVOS;


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

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public List<AnswerCommentVO> getCommentVOS() {
        return commentVOS;
    }

    public void setCommentVOS(List<AnswerCommentVO> commentVOS) {
        this.commentVOS = commentVOS;
    }
}
