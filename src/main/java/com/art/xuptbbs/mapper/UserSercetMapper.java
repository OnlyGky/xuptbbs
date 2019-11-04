package com.art.xuptbbs.mapper;

import com.art.xuptbbs.model.User;
import com.art.xuptbbs.model.UserSercet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserSercetMapper {

    Integer insert(UserSercet userSercet);

    void updatePassword(@Param("userId") String id, @Param("oldPassword") String password,
                        @Param("newPassword") String newPassword);

    User loginByEmail(@Param("email") String email, @Param("password") String password);

    User loginByName(@Param("name") String entrance,@Param("password") String password);

    String checkSame(@Param("name") String nickName, @Param("email")String email);

    String selectEmailById(String id);

     int updatePasswordWithOutLogin(@Param("email")String email,@Param("password") String password);

    List<String> selectemail();
}
