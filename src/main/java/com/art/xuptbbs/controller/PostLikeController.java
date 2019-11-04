package com.art.xuptbbs.controller;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.CodeEnum;
import com.art.xuptbbs.dto.PostLikeDTO;
import com.art.xuptbbs.service.PostLikeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@ResponseBody
public class PostLikeController {
    @Autowired
    PostLikeService postLikeService;

    @PostMapping("postlike")
    public BaseResponse clicklike(@RequestBody PostLikeDTO postLikeDTO){
        postLikeService.clicklike(postLikeDTO);
        return BaseResponse.out(CodeEnum.SUCCESS);
    }

}
