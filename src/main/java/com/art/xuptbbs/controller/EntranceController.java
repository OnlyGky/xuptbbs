package com.art.xuptbbs.controller;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.dto.LoginDTO;
import com.art.xuptbbs.dto.RegisterDTO;
import com.art.xuptbbs.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;


@Controller
@RequestMapping("/entrance")
//@Validated
@ResponseBody
public class EntranceController {

    @Autowired
    UserService userService;

    @PostMapping("/login")

    public BaseResponse bbsLogin(@Validated @RequestBody LoginDTO loginDTO,HttpServletRequest request, HttpServletResponse response){
        return userService.login(loginDTO, request, response);
    }

    @PostMapping("/register")

    public BaseResponse bbsRegister(@Validated @RequestBody RegisterDTO registerDTO){
            return userService.register(registerDTO);
    }

    @PostMapping("/loginout")

    public BaseResponse bbsLoginOut(HttpServletRequest request, HttpServletResponse response){
        return userService.loginOut(request, response);
    }

    @PostMapping("/lost")
    public BaseResponse lost(String email,String code,String password){

        return userService.lost(email, code, password);
    }

}
