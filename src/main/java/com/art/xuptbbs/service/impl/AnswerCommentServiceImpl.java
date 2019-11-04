package com.art.xuptbbs.service.impl;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.CodeEnum;
import com.art.xuptbbs.dto.AnswerCommentDTO;
import com.art.xuptbbs.mapper.AnswerCommentMapper;
import com.art.xuptbbs.mapper.AnswerMapper;
import com.art.xuptbbs.mapper.NotificationMapper;
import com.art.xuptbbs.model.AnswerComment;
import com.art.xuptbbs.model.Notification;
import com.art.xuptbbs.service.AnswerCommentService;
import com.art.xuptbbs.vo.AnswerCommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerCommentServiceImpl implements AnswerCommentService {
    @Autowired
    AnswerCommentMapper answerCommentMapper;

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    NotificationMapper notificationMapper;

    @Override
    public BaseResponse createComment(AnswerCommentDTO answerCommentDTO) {
        AnswerComment answerComment = new AnswerComment();
        BeanUtils.copyProperties(answerCommentDTO, answerComment);
        answerComment.setCreatedAt(LocalDateTime.now());
        answerComment.setLastModified(LocalDateTime.now());
        if (answerCommentMapper.createAnswerComment(answerComment) > 0){
            answerMapper.increateCommentCount(answerComment.getAnswerId());
            Notification notification = new Notification( 5L, answerCommentDTO.getAnswerId(), answerCommentDTO.getContent(),
                    answerCommentDTO.getUserId(), answerCommentDTO.getToUserId(), LocalDateTime.now());
            notificationMapper.saveNotification(notification);
            return BaseResponse.out(CodeEnum.SUCCESS);
        }

        return BaseResponse.out(CodeEnum.FAIL);
    }

    @Override
    public BaseResponse deleteAnswerComment(Long answerId, Long id, String userId) {
        answerCommentMapper.deleteAnswerComment(id, userId);
        answerMapper.decreaseCommentCount(answerId);
        return BaseResponse.out(CodeEnum.SUCCESS);
    }

    @Override
    public List<AnswerCommentVO> getSonAnswerComment(Long id) {
        List<AnswerComment> comments = answerCommentMapper.getSonAnswerComment(id);

        List<AnswerCommentVO> commentVOS = new ArrayList<>();
        if (comments != null){
            for (AnswerComment comment : comments){
                AnswerCommentVO answerCommentVO = new AnswerCommentVO(comment);
                commentVOS.add(answerCommentVO);
            }
        }
        return commentVOS;
    }
}
