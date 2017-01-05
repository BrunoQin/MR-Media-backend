package com.mr.media.response.user;

import com.mr.media.response.BaseResp;

/**
 * Created by i321273 on 1/5/17.
 */
public class LoginResp extends BaseResp {

    public String token;
    public int authority;

    public LoginResp(int errCode, String token, int authority){
        super(errCode);
        this.token = token;
        this.authority = authority;
    }
}
