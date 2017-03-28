package com.mr.media.response.user;

import com.mr.media.response.BaseResp;

import java.util.List;

/**
 * Created by 秦博 on 2017/3/28.
 */
public class LookUpAgentSubEmployeesResp extends BaseResp {

    public static class Employee{
        public String realName;
        public String avatar;
        public Integer level;
        public String tel;
        public String weChat;
        public String idNumber;
    }

    public List<Employee> employees;

    public LookUpAgentSubEmployeesResp(int errCode, List<Employee> employees) {
        super(errCode);
        this.employees = employees;
    }
}
