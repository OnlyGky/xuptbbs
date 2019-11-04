package com.art.xuptbbs.mapper;

import com.art.xuptbbs.model.PostTopic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostTopicMapper {

    void saveTopic(@Param("postId") Long postId, @Param("topic") String topic);

    List<PostTopic> selectTopic();

    List<String> selectOneTopic(Long id);
}
