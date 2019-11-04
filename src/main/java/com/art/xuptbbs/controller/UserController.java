package com.art.xuptbbs.controller;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.CodeEnum;
import com.art.xuptbbs.common.ResponseData;
import com.art.xuptbbs.dto.PasswordDTO;
import com.art.xuptbbs.model.User;
import com.art.xuptbbs.service.UserService;
import com.art.xuptbbs.vo.UserDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Controller
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/upphoto")
    @ResponseBody
    public BaseResponse uploadPhoto(@RequestParam(value = "file")MultipartFile file,
                                    @RequestParam("id") @NotNull String id){
        return userService.uploadPhoto(file,id);
    }

    @PostMapping("/user")
    @ResponseBody
    public BaseResponse updatePassword(@RequestBody PasswordDTO passwordDto){
        return userService.updatePassword(passwordDto);
    }


    @GetMapping("/user/{id}")
    @ResponseBody
    public UserDetailVO getUserProfile(@NotNull @PathVariable String id){
        return userService.getById(id);
    }

    @PostMapping("/up_user")
    @ResponseBody
    public BaseResponse updateUserProfile(@RequestBody User user){
        if (userService.updateUserProfile(user))
           return ResponseData.out(CodeEnum.SUCCESS, new UserDetailVO(user));
        return BaseResponse.out(CodeEnum.SAME_FAIL);
    }


}
