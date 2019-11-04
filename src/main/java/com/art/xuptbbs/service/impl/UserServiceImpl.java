package com.art.xuptbbs.service.impl;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.CodeEnum;
import com.art.xuptbbs.common.ResponseData;
import com.art.xuptbbs.config.FileConfig;
import com.art.xuptbbs.dto.LoginDTO;
import com.art.xuptbbs.dto.PasswordDTO;
import com.art.xuptbbs.dto.RegisterDTO;
import com.art.xuptbbs.exception.BusinessException;
import com.art.xuptbbs.mapper.UserAttachmentMapper;
import com.art.xuptbbs.mapper.UserMapper;
import com.art.xuptbbs.mapper.UserSercetMapper;
import com.art.xuptbbs.model.User;
import com.art.xuptbbs.model.UserSercet;
import com.art.xuptbbs.service.UserService;
import com.art.xuptbbs.util.CodeUtil;
import com.art.xuptbbs.util.JwtUtil;
import com.art.xuptbbs.util.LoginUtil;
import com.art.xuptbbs.util.PhotoUtil;
import com.art.xuptbbs.vo.UserAllDetailVO;
import com.art.xuptbbs.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static java.time.LocalDateTime.now;


@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    @Autowired
    UserSercetMapper userSercetMapper;

    @Autowired
    UserAttachmentMapper userAttachmentMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    FileConfig fileConfig;

    @Override
    public BaseResponse register(RegisterDTO registerDTO) {

        String code = registerDTO.getCode();
        String emailCode = stringRedisTemplate.opsForValue().get(registerDTO.getEmail());
        if (code == null || emailCode == null || !code.equals(emailCode)){
            return  BaseResponse.out(CodeEnum.SAME_FAIL);
        }
        String nickName = registerDTO.getNickname();

        List<String> name = userMapper.selectName();
        if (null == nickName || name.contains(nickName)){
            return BaseResponse.out(CodeEnum.SAME_FAIL);
        }

        String email = registerDTO.getEmail();
        List<String> emails = userSercetMapper.selectemail();
        if (null == email || emails.contains(email)){
            return BaseResponse.out(CodeEnum.SAME_FAIL);
        }
        UserSercet userSercet = new UserSercet();
        String userId = UUID.randomUUID().toString().replaceAll("-","");

        registerDTO.setPassword(LoginUtil.getPassword(registerDTO.getPassword()));
        var now = LocalDateTime.now();
        userSercet.setUserId(userId);
        userSercet.setCreatedAt(now);
        userSercet.setLastModified(now);
        BeanUtils.copyProperties(registerDTO,userSercet);

        userSercetMapper.insert(userSercet);
        User user = new User(userId, registerDTO.getNickname(), "headphoto.jpg", now(), now());
        userMapper.insert(user);
        return BaseResponse.out(CodeEnum.SUCCESS);
    }

    public static boolean checkEmail(String email)
    {// 验证邮箱的正则表达式
        String format = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        if (email.matches(format))
        {
            return true;// 邮箱名合法，返回true
        }
        else
        {
            return false;// 邮箱名不合法，返回false
        }
    }
    @Override
    public BaseResponse login(LoginDTO loginDTO,HttpServletRequest request, HttpServletResponse response) {
//        String code = loginDTO.getCode();
//        String key =  ""+code.hashCode();
//       String rediscode =  stringRedisTemplate.opsForValue().get(key);
//       if(!code.equals(rediscode)){
//           return BaseResponse.out(CodeEnum.EMAIL_FAIL);
//       }
       String password = LoginUtil.getPassword(loginDTO.getPassword());
       String entrance = loginDTO.getEntrance();
        User user = null;
       if (checkEmail(entrance))
        user = userSercetMapper.loginByEmail(entrance,password);
       else
           user = userSercetMapper.loginByName(entrance,password);
       if (user!=null){
           String token = JwtUtil.createToken(user.getId());
           Cookie cookie=new Cookie("token",token);
           response.addCookie(cookie);
           UserVO userVO = new UserVO(user);
           stringRedisTemplate.opsForValue().set(user.getId(),token,1, TimeUnit.DAYS);
           return ResponseData.out(CodeEnum.SUCCESS,userVO);
       }
        return BaseResponse.out(CodeEnum.FAIL);
    }

    @Override
    public BaseResponse loginOut(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        if (stringRedisTemplate.hasKey(id)) {
            stringRedisTemplate.delete(id);
        }
        Cookie cookie = new Cookie("token","");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return BaseResponse.out(CodeEnum.SUCCESS);
    }

    @Override
    public BaseResponse uploadPhoto(MultipartFile file, String id) {

        if (file.isEmpty())
            return BaseResponse.out(CodeEnum.PHOTO_FAIL);
        String storeFilename = PhotoUtil.savePhoto(file, fileConfig.getAvatarUploadFolder());
        userMapper.uploadPhoto(id,storeFilename);
        return ResponseData.out(CodeEnum.SUCCESS, "http://yugengkai.top:8080/xuptbbs/images/avatar/"+storeFilename);
    }

    @Override
    public BaseResponse updatePassword(PasswordDTO passwordDto) {
        if (!passwordDto.getNewPassword().equals(passwordDto.getAgainPassword()))
            return BaseResponse.out(CodeEnum.PSWWORRD_FAIL);
       String password = LoginUtil.getPassword(passwordDto.getPassword());
       String newPassword = LoginUtil.getPassword(passwordDto.getAgainPassword());
        userSercetMapper.updatePassword(passwordDto.getId(), password, newPassword);
            return BaseResponse.out(CodeEnum.SUCCESS);
    }

    @Override
    public UserAllDetailVO getById(String id) {
        var user = userMapper.selectById(id);
        String email = userSercetMapper.selectEmailById(id);
       UserAllDetailVO userAllDetailVO = new UserAllDetailVO(user);
        Map map =  userAttachmentMapper.selectFollowNum(id);
        userAllDetailVO.setFollow((Long)map.get("follow"));
        userAllDetailVO.setFans((Long)map.get("fans"));
        userAllDetailVO.setEmail(email);
       return userAllDetailVO;
    }

    @Override
    public boolean updateUserProfile(User user) {
        if(userMapper.checkSameName(user.getId(), user.getNickname()) != null)
            return false;
        userMapper.updateUserProfile(user);
        return true;
    }

    @Override
    public BaseResponse lost(String email, String code, String password) {
        String emailCode = stringRedisTemplate.opsForValue().get(email);
        if (code == null || emailCode == null || !code.equals(emailCode)){
            return  BaseResponse.out(CodeEnum.EMAIL_FAIL);
        }
        password = LoginUtil.getPassword(password);
        if(userSercetMapper.updatePasswordWithOutLogin(email, password)> 0)
            return BaseResponse.out(CodeEnum.SUCCESS);
        else
            return BaseResponse.out(CodeEnum.FAIL);
    }
}
