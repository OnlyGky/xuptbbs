package com.art.xuptbbs.service;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.dto.LoginDTO;
import com.art.xuptbbs.dto.PasswordDTO;
import com.art.xuptbbs.dto.RegisterDTO;
import com.art.xuptbbs.model.User;
import com.art.xuptbbs.vo.UserDetailVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface UserService {

    BaseResponse register(RegisterDTO registerDTO) ;

    BaseResponse login(LoginDTO loginDTO,HttpServletRequest request, HttpServletResponse response);


    BaseResponse loginOut(HttpServletRequest request, HttpServletResponse response);

    BaseResponse uploadPhoto(MultipartFile file,String id);

    BaseResponse updatePassword(PasswordDTO passwordDto);

    UserDetailVO getById(String id);

    boolean updateUserProfile(User user);

    BaseResponse lost(String email, String code, String password);
}
