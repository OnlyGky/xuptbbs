package com.art.xuptbbs.vo;

import com.art.xuptbbs.model.Comment;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class CommentDetailVO extends CommentVO{
    private UserVO userVO;
    public CommentDetailVO(Comment comment) {
        super(comment.getId(), comment.getContent(), comment.getParentId(), comment.getLastActiveAt());
        BeanUtils.copyProperties(comment,this);
        userVO = new UserVO(comment.getUser());
    }

    public UserVO getUserVO() {
        return userVO;
    }
    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

}
