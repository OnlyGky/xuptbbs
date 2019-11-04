package com.art.xuptbbs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface AnswerLikeMapper {

    List<Long> selectAnswerIdByUserId(String userId);

    void saveLike(@Param("answerId") Long answerId, @Param("userId") String userId, @Param("createdAt") LocalDateTime createdAt);

    void cancelLike(@Param("answerId")Long answerId, @Param("userId") String userId);
}
