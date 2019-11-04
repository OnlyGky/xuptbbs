package com.art.xuptbbs.controller;

import com.art.xuptbbs.common.BaseResponse;
import com.art.xuptbbs.common.ResponseData;
import com.art.xuptbbs.service.NotificationService;
import com.art.xuptbbs.vo.NotificationVO;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@ResponseBody
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/notification")
    public ResponseData<Page<NotificationVO>> selectNotification(
            @RequestParam(value = "id", required = false) String id,
            @Range(min = 1, max = Integer.MAX_VALUE) @RequestParam(defaultValue = "1") int page,
            @Range(min = 1, max = 100) @RequestParam(value = "per_page", defaultValue = "10") int perPage){
        var notificationRequest = PageRequest.of(page-1, perPage);
        return  notificationService.selectNotification(id, notificationRequest);
    }

    @DeleteMapping("/notification")
    public BaseResponse deleteNotification(@RequestParam(value = "id") Long id){
        return notificationService.deleteNotification(id);
    }

    @GetMapping("notification/detail/{id}")
    public NotificationVO selectNotificationDetail(@PathVariable("id") Long id){
        return notificationService.selectNotificationDetail(id);
    }


}
