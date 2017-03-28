package com.mr.media.response.user;

import com.mr.media.model.User;
import com.mr.media.response.BaseResp;

/**
 * Created by tanjingru on 13/03/2017.
 */
public class SubEmployeeDetailResp extends BaseResp{
    public User employee;

    public SubEmployeeDetailResp(int errCode, User employee) {
        super(errCode);
        this.employee = employee;
    }
}
