package com.mr.media.response.user;

import com.mr.media.response.BaseResp;

/**
 * Created by i321273 on 1/5/17.
 */
public class GetProfileResp extends BaseResp {

    public static class Profile{
        public String uid;
        public String realName;
    }

    public Profile profile;

    public GetProfileResp(int errCode, Profile profile){
        super(errCode);
        this.profile = profile;
    }

}
