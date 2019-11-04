package com.art.xuptbbs.mapper;

import com.art.xuptbbs.model.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NotificationMapper {

    List<Notification> selectNotification(String id);

    void deleteNotification(Long id);

    Notification selectNotificationDetail(Long id);

    void updateBeRead(Long id);

    void saveNotification(Notification notification);
}
