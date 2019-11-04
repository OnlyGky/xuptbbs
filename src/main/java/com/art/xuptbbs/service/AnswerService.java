package com.art.xuptbbs.service;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.dto.AnswerDTO;
import com.art.xuptbbs.vo.AnswerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnswerService {
    BaseResponse createAnswer(AnswerDTO answerDTO);

    Page<AnswerVO> selectAnswerById(Long id, String userId, Pageable pageable);

    BaseResponse deleteAnswer(Long id, String userId);
}
