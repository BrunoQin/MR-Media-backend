package com.mr.media.response.settle;

import com.mr.media.response.BaseResp;

import java.util.Date;
import java.util.List;

/**
 * Created by tanjingru on 29/03/2017.
 */

public class GetAllSettleResp extends BaseResp {
    public List<SettleInfo> settles;

    public static class SettleInfo{
        public String uid;
        public String platform;
        public String platformName;
        public String platformId;
        public Date date;
        public int amount;
    }

    public GetAllSettleResp(int errCode, List<SettleInfo> settles) {
        super(errCode);
        this.settles = settles;
    }
}
