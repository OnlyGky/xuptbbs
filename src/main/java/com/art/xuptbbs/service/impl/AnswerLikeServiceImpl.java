package com.art.xuptbbs.service.impl;

import com.art.xuptbbs.dto.AnswerLikeDTO;
import com.art.xuptbbs.mapper.AnswerLikeMapper;
import com.art.xuptbbs.mapper.AnswerMapper;
import com.art.xuptbbs.mapper.NotificationMapper;
import com.art.xuptbbs.model.Notification;
import com.art.xuptbbs.service.AnswerLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnswerLikeServiceImpl implements AnswerLikeService {

    @Autowired
    AnswerLikeMapper answerLikeMapper;

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    NotificationMapper notificationMapper;
    @Override
    public void clicklike(AnswerLikeDTO answerLikeDTO) {
        if (answerLikeDTO.getType() == 0){
            answerLikeMapper.saveLike(answerLikeDTO.getAnswerId(), answerLikeDTO.getMyId(), LocalDateTime.now());
            answerMapper.increateLikeCount(answerLikeDTO.getAnswerId());
            Notification notification = new Notification( 3L, answerLikeDTO.getAnswerId(), answerLikeDTO.getSummary(),
                    answerLikeDTO.getMyId(), answerLikeDTO.getUserId(), LocalDateTime.now());
            notification.setCreatedAt(LocalDateTime.now());
            notificationMapper.saveNotification(notification);
        }else {
            answerLikeMapper.cancelLike(answerLikeDTO.getAnswerId(), answerLikeDTO.getMyId());
            answerMapper.decreaseLikeCount(answerLikeDTO.getAnswerId());
        }
    }
}
