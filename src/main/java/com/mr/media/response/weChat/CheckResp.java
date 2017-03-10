package com.mr.media.response.weChat;

import com.mr.media.response.BaseResp;

/**
 * Created by Bruno on 14/01/2017.
 */

public class CheckResp extends BaseResp {

    public String openId;

    public CheckResp(int errCode, String openId){
        super(errCode);
        this.openId = openId;
    }

}
