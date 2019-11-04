package com.art.xuptbbs.controller;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.dto.AnswerCommentDTO;
import com.art.xuptbbs.service.AnswerCommentService;
import com.art.xuptbbs.vo.AnswerCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@ResponseBody
public class AnswerCommentController {

    @Autowired
    AnswerCommentService answerCommentService;

    @PostMapping("answercomment")
    public BaseResponse createComment(@NotNull @RequestBody AnswerCommentDTO answerCommentDTO){
        return answerCommentService.createComment(answerCommentDTO);
    }

    @DeleteMapping("answercomment")
    public BaseResponse deleteAnswerComment(Long answerId, Long id, String userId){
        return answerCommentService.deleteAnswerComment(answerId, id, userId);
    }

    @GetMapping("answercomment/{id}")
    public List<AnswerCommentVO> getSonAnswerComment(@NotNull @PathVariable("id") Long id){
        return answerCommentService.getSonAnswerComment(id);
    }

}
