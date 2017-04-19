package com.mr.media.response.authority.admin;

import com.mr.media.model.Admin;
import com.mr.media.response.BaseResp;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * Created by tanjingru on 29/03/2017.
 */

public class GetAllAdminResp extends BaseResp{

    public List<AdminResp> admin;

    public GetAllAdminResp(int errCode, List<AdminResp> admin) {
        super(errCode);
        this.admin = admin;
    }

    static public class AdminResp{
        public Integer id;
        public String name;
        public String phone;
        public List<Integer> auth;
    }
}
