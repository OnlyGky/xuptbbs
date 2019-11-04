package com.art.xuptbbs.mapper;

import com.art.xuptbbs.model.PostLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface PostLikeMapper {


    List<Long> selectPostIdByUserId(String id);

    void saveLike(@Param("postId") Long postId,@Param("userId") String userId,@Param("createdAt") LocalDateTime now);

    void cancelLike(@Param("postId")Long postId, @Param("userId")String userId);
}
