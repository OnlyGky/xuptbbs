package com.art.xuptbbs.controller;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.dto.AnswerDTO;
import com.art.xuptbbs.service.AnswerService;
import com.art.xuptbbs.vo.AnswerVO;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@ResponseBody
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @PostMapping("answer")
    public BaseResponse createAnswer(@RequestBody AnswerDTO answerDTO){
        return answerService.createAnswer(answerDTO);
    }

    /**
     * 查询一篇文章的所有回答
     * @param id
     * @param userId
     * @param page
     * @param perPage
     * @return
     */
    @GetMapping("answer")
    public Page<AnswerVO> selectAnswerByPostId(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "userId", required = false) String userId,
            @Range(min = 1, max = Integer.MAX_VALUE) @RequestParam(defaultValue = "1") int page,
            @Range(min = 1, max = 100) @RequestParam(value = "per_page", defaultValue = "10") int perPage){
        var pageRequest = PageRequest.of(page - 1, perPage);
        Page<AnswerVO> answerPage = null;
        answerPage = answerService.selectAnswerById(id, userId, pageRequest);
        return answerPage;
    }

    @DeleteMapping("answer")
    public BaseResponse deleteAnswer(@RequestParam("id") Long id, @RequestParam("userId") String userId){
        return answerService.deleteAnswer(id, userId);
    }


}
