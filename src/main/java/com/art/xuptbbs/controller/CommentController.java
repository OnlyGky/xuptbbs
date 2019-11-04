package com.art.xuptbbs.controller;


import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.dto.CommentDTO;
import com.art.xuptbbs.service.CommentService;
import com.art.xuptbbs.vo.CommentDetailVO;
import com.art.xuptbbs.vo.CommentVO;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@ResponseBody
public class CommentController {

    @Autowired
    CommentService commentService;


    @GetMapping("/comment/{id}")
    public List<CommentDetailVO> getSonComment(@NotNull @PathVariable("id") Long id){
        return commentService.getSonComment(id);
    }

    @PostMapping("/comment")
    public BaseResponse createComment(@NotNull @RequestBody CommentDTO commentDTO){
        return commentService.createComment(commentDTO);
    }

    @DeleteMapping("/comment")
    public BaseResponse deleteComment(Long id, String userId){
        return commentService.deleteComment(id,userId);
    }



}
