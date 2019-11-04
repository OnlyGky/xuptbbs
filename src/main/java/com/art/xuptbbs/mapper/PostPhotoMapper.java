package com.art.xuptbbs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Mapper
public interface PostPhotoMapper {

    void savePhoto(@Param("id") Long id, @Param("path") String path,
                   @Param("createdAt") LocalDateTime createdAt);
}
