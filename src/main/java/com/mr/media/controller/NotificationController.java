package com.mr.media.controller;

import com.mr.media.model.Notification;
import com.mr.media.request.notification.MarkNotificationReq;
import com.mr.media.response.BaseResp;
import com.mr.media.response.notification.GetPagedNotificationsResp;
import com.mr.media.service.NotificationService;
import com.mr.media.tool.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 秦博 on 2017/3/11.
 */

@RestController
@RequestMapping(value = "/notification")
public class NotificationController {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "/get_related_notifications", method = RequestMethod.GET)
    public BaseResp getPagedNotifications(String token, Integer pageId, Integer pageSize, Integer status){
        Pair<Integer, List<Notification>> pair = notificationService.getPagedNotifications(token, pageId, pageSize, status);

        List<GetPagedNotificationsResp.Notification> notifications = pair.getSecond().stream().map(
                o -> {
                    GetPagedNotificationsResp.Notification notification = new GetPagedNotificationsResp.Notification();
                    notification.notificationId = o.getId();
                    notification.content = o.getTextContent();
                    notification.sender = o.getSender();
                    notification.reciewer = o.getReceiver();
                    notification.whenCreated = o.getWhenCreated();
                    return notification;
                }
        ).collect(Collectors.toList());
        return new GetPagedNotificationsResp(BaseResp.SUCCESS, pageId, pageSize, pair.getFirst(), notifications);
    }

    @RequestMapping(value = "mark_notification", method = RequestMethod.POST)
    public BaseResp markNotification(@RequestBody MarkNotificationReq markNotificationReq){

        int errCode = notificationService.markNotification(markNotificationReq.notificationId, markNotificationReq.status);
        return new BaseResp(errCode);

    }

}
