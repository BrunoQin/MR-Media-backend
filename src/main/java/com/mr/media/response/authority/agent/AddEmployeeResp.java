package com.mr.media.response.authority.agent;

import com.mr.media.request.BaseReq;
import com.mr.media.response.BaseResp;

/**
 * Created by tanjingru on 06/01/2017.
 */
public class AddEmployeeResp extends BaseResp {

    public static class Employee{
        public String uid;
    }

    public Employee employee;

    public AddEmployeeResp(int errCode, Employee employee) {
        super(errCode);
        this.employee = employee;
    }
}
