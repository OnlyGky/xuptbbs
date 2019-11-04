package com.art.xuptbbs.controller;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.service.CodeService;
import com.art.xuptbbs.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;

@Controller
@Validated
public class CodeController {

    @Autowired
    EmailService emailService;

    @Autowired
    CodeService codeService;

    @GetMapping ("/code/{email}")
    @ResponseBody
    public BaseResponse getEmailCode(@Email
                                         @PathVariable("email") String email){

        return emailService.getEmailCode(email);
    }

    @GetMapping("/code")
    public void getPhotoCode(HttpServletRequest request, HttpServletResponse response){
        codeService.getCheckPhoto(request,response);
        return;
    }


}
