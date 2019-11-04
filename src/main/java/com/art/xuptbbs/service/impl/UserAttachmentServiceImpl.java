package com.art.xuptbbs.service.impl;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.CodeEnum;
import com.art.xuptbbs.mapper.NotificationMapper;
import com.art.xuptbbs.mapper.UserAttachmentMapper;
import com.art.xuptbbs.model.Notification;
import com.art.xuptbbs.model.User;
import com.art.xuptbbs.model.UserAttachment;
import com.art.xuptbbs.service.UserAttachmentService;
import com.art.xuptbbs.vo.UserDetailVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserAttachmentServiceImpl implements UserAttachmentService {


    @Autowired
    UserAttachmentMapper userAttachmentMapper;

    @Autowired
    NotificationMapper notificationMapper;
    @Override
    public BaseResponse createUserAttachment(String fromUserId, String toUserId) {

        UserAttachment userAttachment = new UserAttachment(fromUserId, toUserId);
        userAttachment.setCreatedAt(LocalDateTime.now());
        userAttachmentMapper.createUserAttachment(userAttachment);
        Notification notification = new Notification(4L, 0L, null, fromUserId, toUserId, LocalDateTime.now());
        notificationMapper.saveNotification(notification);
        return BaseResponse.out(CodeEnum.SUCCESS);
    }

    @Override
    public Page<UserDetailVO> selectMyfans(String id, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        List<User> users = userAttachmentMapper.selectMyfans(id);
        List<UserDetailVO> userDetailVOS = new ArrayList<>();
        for (User user : users){
            userDetailVOS.add(new UserDetailVO(user));
        }
        var pageInfo = new PageInfo<>(userDetailVOS);
        return new PageImpl<>(userDetailVOS, pageable, pageInfo.getTotal());
    }

    @Override
    public Page<UserDetailVO> selectfollow(String id, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        List<User> users = userAttachmentMapper.selectfollow(id);
        List<UserDetailVO> userDetailVOS = new ArrayList<>();
        for (User user : users){
            userDetailVOS.add(new UserDetailVO(user));
        }
        BeanUtils.copyProperties(users, userDetailVOS);
        var pageInfo = new PageInfo<>(userDetailVOS);
        return new PageImpl<>(userDetailVOS, pageable, pageInfo.getTotal());
    }

    @Override
    public void deleteFollow(String fromUserId, String toUserId) {
        userAttachmentMapper.deleteFollow(fromUserId,toUserId);
    }
}
