package com.art.xuptbbs.mapper;

import com.art.xuptbbs.dto.CommentDTO;
import com.art.xuptbbs.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {


    List<Comment> selectCommentById(Long id);

    List<Comment> getSonComment(Long id);

    Long createComment(Comment comment);

    Long deleteComment(@Param("id") Long id, @Param("userId") String userId);
}
