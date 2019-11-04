package com.art.xuptbbs.service.impl;


import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.CodeEnum;
import com.art.xuptbbs.common.ResponseData;
import com.art.xuptbbs.mapper.NotificationMapper;
import com.art.xuptbbs.model.Notification;
import com.art.xuptbbs.service.NotificationService;
import com.art.xuptbbs.vo.NotificationVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationMapper notificationMapper;

    @Override
    public ResponseData<Page<NotificationVO>> selectNotification(String id, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber()-1, pageable.getPageSize());
        List<Notification> notifications = null;
        notifications = notificationMapper.selectNotification(id);
        boolean isNoRead = false;
        List<NotificationVO> notificationVOS = new ArrayList<>();
        for (Notification notification : notifications){
            if (!isNoRead && 0 == notification.getBeRead())
                isNoRead = true;
                notificationVOS.add(new NotificationVO(notification));
        }
        var pageInfo = new PageInfo<>(notificationVOS);
        Page<NotificationVO> notificationPage =  new PageImpl<>(notificationVOS, pageable, pageInfo.getTotal());
        if (isNoRead)
        return ResponseData.out(CodeEnum.SUCCESS, notificationPage);
        else return ResponseData.out(CodeEnum.FAIL, notificationPage);
    }

    @Override
    public BaseResponse deleteNotification(Long id) {
        notificationMapper.deleteNotification(id);
        return BaseResponse.out(CodeEnum.SUCCESS);
    }

    @Override
    public NotificationVO selectNotificationDetail(Long id) {
        Notification notification = notificationMapper.selectNotificationDetail(id);
        if (null != notification){
            notificationMapper.updateBeRead(id);
        }
        NotificationVO notificationVO = new NotificationVO(notification);
        return notificationVO;
    }

    @Override
    public void saveNotification(Notification notification) {
        notification.setCreatedAt(LocalDateTime.now());
        notification.setLastModified(LocalDateTime.now());
        notificationMapper.saveNotification(notification);
    }
}
