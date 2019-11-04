package com.art.xuptbbs.service;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.ResponseData;
import com.art.xuptbbs.model.Notification;
import com.art.xuptbbs.vo.NotificationVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface NotificationService {

    ResponseData<Page<NotificationVO>> selectNotification(String id, Pageable pageable);

    BaseResponse deleteNotification(Long id);

    NotificationVO selectNotificationDetail(Long id);

    void saveNotification(Notification notification);
}
