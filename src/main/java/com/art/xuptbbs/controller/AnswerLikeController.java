package com.art.xuptbbs.controller;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.CodeEnum;
import com.art.xuptbbs.dto.AnswerLikeDTO;
import com.art.xuptbbs.service.AnswerLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@ResponseBody
public class AnswerLikeController {

    @Autowired
    AnswerLikeService answerLikeService;

    @PostMapping("answerlike")
    public BaseResponse clicklike(@RequestBody AnswerLikeDTO answerLikeDTO){
        answerLikeService.clicklike(answerLikeDTO);
        return BaseResponse.out(CodeEnum.SUCCESS);
    }
}
