package com.art.xuptbbs.mapper;

import com.art.xuptbbs.model.User;
import com.art.xuptbbs.model.UserSercet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    Integer insert(User user);

    void uploadPhoto(@Param("userId") String userId, @Param("avatarUrl") String storeFilename);

    User selectById(@Param("id") String id);

    void updateUserProfile(User user);

    String checkSameName(@Param("id") String id, @Param("nickname") String nickname);


    List<String> selectName();
}
