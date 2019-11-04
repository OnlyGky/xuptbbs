package com.art.xuptbbs.vo;

import com.art.xuptbbs.model.Post;

import java.util.List;

public class PostQuestionVO extends CommonPostVO{
    private List<AnswerVO> answerVOList;

    public PostQuestionVO(Post post) {
        super(post);
    }

    public List<AnswerVO> getAnswerVOList() {
        return answerVOList;
    }

    public void setAnswerVOList(List<AnswerVO> answerVOList) {
        this.answerVOList = answerVOList;
    }
}
