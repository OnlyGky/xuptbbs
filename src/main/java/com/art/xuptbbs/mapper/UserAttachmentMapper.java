package com.art.xuptbbs.mapper;

import com.art.xuptbbs.model.User;
import com.art.xuptbbs.model.UserAttachment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserAttachmentMapper {


    Map<Long,Long> selectFollowNum(String id);

    void createUserAttachment(UserAttachment userAttachment);

    List<User> selectMyfans(String id);

    List<User> selectfollow(String id);

    void deleteFollow(@Param("fromUserId") String fromUserId, @Param("toUserId") String toUserId);
}
