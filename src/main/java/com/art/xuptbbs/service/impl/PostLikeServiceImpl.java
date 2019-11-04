package com.art.xuptbbs.service.impl;

import com.art.xuptbbs.dto.PostLikeDTO;
import com.art.xuptbbs.mapper.NotificationMapper;
import com.art.xuptbbs.mapper.PostLikeMapper;
import com.art.xuptbbs.mapper.PostMapper;
import com.art.xuptbbs.model.Notification;
import com.art.xuptbbs.service.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostLikeServiceImpl implements PostLikeService{

    @Autowired
    PostLikeMapper postLikeMapper;

    @Autowired
    PostMapper postMapper;

    @Autowired
    NotificationMapper notificationMapper;

    @Override
    public void clicklike(PostLikeDTO postLikeDTO) {

        //1评论 2 回答 3 点赞 4 关注 5 评论回答 6 收藏
        if (postLikeDTO.getType() == 0){
            postLikeMapper.saveLike(postLikeDTO.getPostId(), postLikeDTO.getMyId(), LocalDateTime.now());
            postMapper.increateLikeCount(postLikeDTO.getPostId());
            Notification notification = new Notification( 3L, postLikeDTO.getPostId(), postLikeDTO.getSummary(),
                    postLikeDTO.getMyId(), postLikeDTO.getUserId(), LocalDateTime.now());
            notification.setCreatedAt(LocalDateTime.now());
            notificationMapper.saveNotification(notification);
        } else{
            postLikeMapper.cancelLike(postLikeDTO.getPostId(), postLikeDTO.getMyId());
            postMapper.decreaseLikeCount(postLikeDTO.getPostId());
        }
    }
}
