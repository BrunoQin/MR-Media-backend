package com.mr.media.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.PagedList;
import com.mr.media.model.Notification;
import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.tool.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 秦博 on 2017/3/11.
 */

@Service
public class NotificationService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    public Notification findNotificationBuId(int id){
        return Ebean.find(Notification.class).where()
                .eq("id", id)
                .findUnique();
    }

    public Pair<Integer, List<Notification>> getPagedNotifications(String token, Integer pageId, Integer pageSize, Integer status){

        User receiver = userService.findUserByToken(token);
        // 检查status是否合法
        if(status != Notification.READ_NOTIFICATION && status != Notification.UNREAD_NOTIFICATION){
            return new Pair(new BaseResp(BaseResp.GET_NOTIFICATION_WRONG_STATUS), null);
        }
        ExpressionList<Notification> el = Ebean.find(Notification.class).where();
        el = el.eq("receiver", receiver).eq("status", status);
        PagedList<Notification> pl = el.findPagedList(pageId, pageSize);
        int totalPages = pl.getTotalPageCount();
        List<Notification> notifications = pl.getList();
        return new Pair<>(totalPages, notifications);

    }

    public int markNotification(Integer notificationId, Integer status){

        if(status != Notification.READ_NOTIFICATION && status != Notification.UNREAD_NOTIFICATION){
            return BaseResp.GET_NOTIFICATION_WRONG_STATUS;
        }

        Notification notification = findNotificationBuId(notificationId);

        if(status == notification.getStatus()){
            return BaseResp.MARK_NOTIFICATION_SAME_STATUS;
        }

        notification.setStatus(status);
        try{
            notification.save();
            return BaseResp.SUCCESS;
        } catch (Exception e){
            logger.error("更改状态码失败", e);
            return BaseResp.UNKNOWN;
        }

    }

}
