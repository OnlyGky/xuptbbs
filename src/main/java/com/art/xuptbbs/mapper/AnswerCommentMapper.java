package com.art.xuptbbs.mapper;

import com.art.xuptbbs.model.AnswerComment;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnswerCommentMapper {

    Long createAnswerComment(AnswerComment answerComment);

    Long deleteAnswerComment(@Param("id") Long id, @Param("userId") String userId);

    List<AnswerComment> getSonAnswerComment(Long id);

    List<AnswerComment> getParentAnswerComment(Long id);
}
