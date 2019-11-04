package com.art.xuptbbs.service.impl;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.CodeEnum;
import com.art.xuptbbs.common.ResponseData;
import com.art.xuptbbs.dto.AnswerDTO;
import com.art.xuptbbs.mapper.AnswerCommentMapper;
import com.art.xuptbbs.mapper.AnswerLikeMapper;
import com.art.xuptbbs.mapper.AnswerMapper;
import com.art.xuptbbs.mapper.NotificationMapper;
import com.art.xuptbbs.model.Answer;
import com.art.xuptbbs.model.AnswerComment;
import com.art.xuptbbs.model.Notification;
import com.art.xuptbbs.service.AnswerService;
import com.art.xuptbbs.vo.AnswerCommentVO;
import com.art.xuptbbs.vo.AnswerVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    AnswerLikeMapper answerLikeMapper;

    @Autowired
    NotificationMapper notificationMapper;

    @Autowired
    AnswerCommentMapper answerCommentMapper;
    @Override
    public BaseResponse createAnswer(AnswerDTO answerDTO) {
        Answer answer = new Answer();
        BeanUtils.copyProperties(answerDTO, answer);
        answer.setLastModified(LocalDateTime.now());
        answer.setCreatedAt(LocalDateTime.now());
        answerMapper.createAnswer(answer);
        Notification notification = new Notification( 2L, answerDTO.getQuestionId(), answerDTO.getContent(),
                answerDTO.getUserId(), answerDTO.getTouserId(), LocalDateTime.now());
        notification.setCreatedAt(LocalDateTime.now());
        notificationMapper.saveNotification(notification);
        return ResponseData.out(CodeEnum.SUCCESS,answer.getId());
    }

    @Override
    public Page<AnswerVO> selectAnswerById(Long id, String userId, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        List<Answer> answer = null;
        List<Long> answerLike = null;
        List<AnswerVO> answerVOS = new ArrayList<>();
        answer = answerMapper.selectAnswerById(id);
        if (null != userId){
            answerLike = answerLikeMapper.selectAnswerIdByUserId(userId);
        }
        for (Answer answer1 : answer) {
                AnswerVO answerVO = new AnswerVO(answer1);
                if (null != userId){
                    if (answerLike.contains(answer1.getId()))
                        answerVO.setIsLike(1);
                }
                answerVO.setCommentVOS(this.getParentAnswerComment(answer1.getId()));
                answerVOS.add(answerVO);
        }
        var pageInfo = new PageInfo<>(answerVOS);
        return new PageImpl<>(answerVOS, pageable, pageInfo.getTotal());
    }

    @Override
    public BaseResponse deleteAnswer(Long id, String userId) {

       if (answerMapper.deleteAnswer(id, userId) !=0)
        return BaseResponse.out(CodeEnum.SUCCESS);

       return BaseResponse.out(CodeEnum.FAIL);
    }

    public List<AnswerCommentVO> getParentAnswerComment(Long id) {
        List<AnswerComment> comments = answerCommentMapper.getParentAnswerComment(id);

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
