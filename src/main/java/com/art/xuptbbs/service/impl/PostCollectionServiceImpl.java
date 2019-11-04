package com.art.xuptbbs.service.impl;

import com.art.xuptbbs.dto.PostCollectionDTO;
import com.art.xuptbbs.mapper.NotificationMapper;
import com.art.xuptbbs.mapper.PostCollectionMapper;
import com.art.xuptbbs.mapper.PostMapper;
import com.art.xuptbbs.model.Notification;
import com.art.xuptbbs.service.PostCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Service
public class PostCollectionServiceImpl implements PostCollectionService {
    @Autowired
    PostCollectionMapper postCollectionMapper;

    @Autowired
    PostMapper postMapper;

    @Autowired
    NotificationMapper notificationMapper;
    @Override
    public void clickcollection(PostCollectionDTO postCollectionDTO) {

        if (postCollectionDTO.getType() == 0){
            postCollectionMapper.saveCollection(postCollectionDTO.getPostId(), postCollectionDTO.getUserId(), LocalDateTime.now());
            postMapper.increateJoinCount(postCollectionDTO.getPostId());

            Notification notification = new Notification( 6L, postCollectionDTO.getPostId(), postCollectionDTO.getSummary(),
                    postCollectionDTO.getMyId(), postCollectionDTO.getUserId(), LocalDateTime.now());
            notificationMapper.saveNotification(notification);
        } else{
            postCollectionMapper.cancelCollection(postCollectionDTO.getPostId(), postCollectionDTO.getUserId());
            postMapper.decreaseJoinCount(postCollectionDTO.getPostId());
        }
    }
}
