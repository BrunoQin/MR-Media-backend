package com.mr.media.response.settle;

import com.mr.media.model.Settle;
import com.mr.media.response.BaseResp;

import java.util.List;

/**
 * Created by tanjingru on 29/03/2017.
 */

public class GetAllSettleResp extends BaseResp {
    public List<Settle> settles;

    public GetAllSettleResp(int errCode, List<Settle> settles) {
        super(errCode);
        this.settles = settles;
    }
}
