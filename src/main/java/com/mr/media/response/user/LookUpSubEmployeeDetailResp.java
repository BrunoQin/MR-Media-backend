package com.mr.media.response.user;

import com.mr.media.model.User;
import com.mr.media.response.BaseResp;

import java.util.List;

/**
 * Created by tanjingru on 13/03/2017.
 */
public class LookUpSubEmployeeDetailResp extends BaseResp{

    public static class Platform{
        public String name;
        public String uid;
        public int validDay;
        public int validHour;
        public int giftCount;
        public int settleCount;
    }

    public static class Employee{
        public String realName;
        public Integer level;
        public String avatar;
        public String tel;
        public String weChat;
        public String parentName;
        public Integer settleType;
        public String settleCount;
        public String idNumber;
    }

    public Employee employee;
    public List<Platform> platforms;

    public LookUpSubEmployeeDetailResp(int errCode, Employee employee, List<Platform> platforms) {
        super(errCode);
        this.employee = employee;
        this.platforms = platforms;
    }
}
