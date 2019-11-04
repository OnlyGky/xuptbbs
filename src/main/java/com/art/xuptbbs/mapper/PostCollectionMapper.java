package com.art.xuptbbs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface PostCollectionMapper {

    List<Long> selectPostIdByUserId(String id);

    void saveCollection(@Param("postId") Long postId, @Param("userId") String userId, @Param("createdAt") LocalDateTime now);

    void cancelCollection(@Param("postId")Long postId, @Param("userId")String userId);
}
