package com.art.xuptbbs.controller;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.CodeEnum;
import com.art.xuptbbs.service.UserAttachmentService;
import com.art.xuptbbs.vo.UserDetailVO;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@Validated
@ResponseBody
public class UserAttachmentController {
    @Autowired
    UserAttachmentService userAttachmentService;

    @PostMapping("userattachment")
    public BaseResponse createUserAttachment(@RequestParam @NotNull String fromUserId,
                                             @RequestParam @NotNull String toUserId){
        return userAttachmentService.createUserAttachment(fromUserId, toUserId);
    }

    @GetMapping("userattachment/fans")
    public Page<UserDetailVO> selectMyfans(
            @RequestParam(value = "id") String id,
            @Range(min = 1, max = Integer.MAX_VALUE) @RequestParam(defaultValue = "1") int page,
            @Range(min = 1, max = 100) @RequestParam(value = "per_page", defaultValue = "10") int perPage){
        var pageRequest = PageRequest.of(page - 1, perPage);
        Page<UserDetailVO> userDetailVOPage;
        userDetailVOPage = userAttachmentService.selectMyfans(id, pageRequest);
        return userDetailVOPage;

    }

    @GetMapping("userattachment/follow")
    public Page<UserDetailVO> selectMyfollow(
            @RequestParam(value = "id") String id,
            @Range(min = 1, max = Integer.MAX_VALUE) @RequestParam(defaultValue = "1") int page,
            @Range(min = 1, max = 100) @RequestParam(value = "per_page", defaultValue = "10") int perPage){
        var pageRequest = PageRequest.of(page - 1, perPage);
        Page<UserDetailVO> userDetailVOPage;
        userDetailVOPage = userAttachmentService.selectfollow(id, pageRequest);
        return userDetailVOPage;
    }

    @DeleteMapping("userattachment")
    public BaseResponse deleteFollow(@RequestParam @NotNull String fromUserId,
                                     @RequestParam @NotNull String toUserId){
        userAttachmentService.deleteFollow(fromUserId, toUserId);
        return BaseResponse.out(CodeEnum.SUCCESS);
    }

}
