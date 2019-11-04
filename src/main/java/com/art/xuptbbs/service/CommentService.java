package com.art.xuptbbs.service;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.dto.CommentDTO;
import com.art.xuptbbs.vo.CommentDetailVO;

import java.util.List;

public interface CommentService {

    List<CommentDetailVO> getSonComment(Long id);

    BaseResponse createComment(CommentDTO commentDTO);

    BaseResponse deleteComment(Long id, String userId);
}
