package com.art.xuptbbs.controller;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.CodeEnum;
import com.art.xuptbbs.dto.PostCollectionDTO;
import com.art.xuptbbs.dto.PostLikeDTO;
import com.art.xuptbbs.service.PostCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@ResponseBody
public class PostCollectionController {

    @Autowired
    PostCollectionService postCollectionService;

    @PostMapping("postcollection")
    public BaseResponse clickcollection(@RequestBody PostCollectionDTO postCollectionDTO){
        postCollectionService.clickcollection(postCollectionDTO);
        return BaseResponse.out(CodeEnum.SUCCESS);
    }

}
