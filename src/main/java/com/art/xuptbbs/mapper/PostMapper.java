package com.art.xuptbbs.mapper;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.enumeration.Board;
import com.art.xuptbbs.enumeration.Classification;
import com.art.xuptbbs.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface PostMapper {


    Long createPost(Post post);

    List<Post> selectByBoard(Board board);

    Post selectPostDetail(Long id);

    List<Post> secletByTopic(Long topicId);

    List<Post> selectPostByCassification(Classification classification);

    void deletePost(Long id);



    List<Post> selectMyPost(String id);

    void updatePost(@Param("id") Long id, @Param("title") String title, @Param("content") String content,
                    @Param("lastActiveAt") LocalDateTime now);


    void increateLikeCount(@Param("postId") Long postId);

    void decreaseLikeCount(Long postId);

    void decreaseJoinCount(Long postId);

    void increateJoinCount(Long postId);

    void incermentLook(Long id);

    List<Post> selectMyCollection(String id);

    List<Post> selectMyquestion(String id);

    LocalDateTime test(String id);
}
