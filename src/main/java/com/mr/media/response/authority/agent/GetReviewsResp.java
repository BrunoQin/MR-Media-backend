package com.mr.media.response.authority.agent;

import com.mr.media.response.BaseResp;

import java.util.List;

/**
 * Created by tanjingru on 10/04/2017.
 */
public class GetReviewsResp extends BaseResp {
    public GetReviewsResp(int errCode) {
        super(errCode);
    }

    static public class Person{
        public String id;
        public Integer type;
        public Integer status;
        public String name;
        public String phone;
        public String wechat;
        public String father;
        public String idCard;
    }

    public GetReviewsResp(int errCode, List<Person> verifyData) {
        super(errCode);
        this.verifyData = verifyData;
    }

    public List<Person> verifyData;

    public List<Person> getVerifyData() {
        return verifyData;
    }

    public void setVerifyData(List<Person> verifyData) {
        this.verifyData = verifyData;
    }
}
