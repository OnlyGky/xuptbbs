package com.art.xuptbbs.mapper;

import com.art.xuptbbs.model.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnswerMapper {

    Long createAnswer(Answer answer);

    List<Answer> selectAnswerById(Long id);


    Long deleteAnswer(@Param("id") Long id, @Param("userId") String userId);

    void increateLikeCount(Long answerId);

    void decreaseLikeCount(Long answerId);


    void increateCommentCount(Long answerId);

    void decreaseCommentCount(Long id);
}
