package com.mr.media.response.authority.admin;

import com.mr.media.model.Admin;
import com.mr.media.response.BaseResp;

import java.util.List;

/**
 * Created by tanjingru on 29/03/2017.
 */

public class GetAllAdminResp extends BaseResp{

    static public class AdminRespEntity{
        public Admin admin;
        public List<Integer> authorities;

        public AdminRespEntity(Admin admin, List<Integer> authorities) {
            this.admin = admin;
            this.authorities = authorities;
        }
    }

    public List<AdminRespEntity> admins;

    public GetAllAdminResp(int errCode, List<AdminRespEntity> admins) {
        super(errCode);
        this.admins = admins;
    }

    public GetAllAdminResp(int errCode) {
        super(errCode);

    }
}
