package com.mr.media.response.notification;

import com.mr.media.model.User;
import com.mr.media.response.BaseResp;

import java.util.Date;
import java.util.List;

/**
 * Created by 秦博 on 2017/3/11.
 */
public class GetPagedNotificationsResp extends BaseResp{

    public static class Notification{
        public Integer notificationId;
        public String content;
        public User sender;
        public User reciewer;
        public Date whenCreated;
    }

    public int pageId;
    public int pageSize;
    public int totalpage;
    public List<Notification> notifications;

    public GetPagedNotificationsResp(int errCode, int pageId, int pageSize, int totalpage, List<Notification> notifications) {
        super(errCode);
        this.pageId = pageId;
        this.pageSize = pageSize;
        this.totalpage = totalpage;
        this.notifications = notifications;
    }
}
