package com.art.xuptbbs.service;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.dto.AnswerCommentDTO;
import com.art.xuptbbs.vo.AnswerCommentVO;

import java.util.List;

public interface AnswerCommentService {
    BaseResponse createComment(AnswerCommentDTO answerCommentDTO);


    BaseResponse deleteAnswerComment(Long answerId, Long id, String userId);

    List<AnswerCommentVO> getSonAnswerComment(Long id);
}
