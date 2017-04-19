package com.mr.media.response.review;

import com.mr.media.response.BaseResp;

import java.util.List;

/**
 * Created by tanjingru on 29/03/2017.
 */

public class GetAllReviewsResp extends BaseResp{

    public GetAllReviewsResp(int errCode, GetAllReviewsRespData verifyData) {
        super(errCode);
        VerifyData = verifyData;
    }

    static public class ReviewActor {
        public String id;
        public String name;
        public Integer status;
        public String avatar;
        public String phone;
        public String father;
        public String idCard;
        public String location;
        public Integer payType;
        public String payAccount;
        public List<Integer> skills;
        public String wechat;
        public List<String> idPics;
        public List<String> photo;
    }

    static public class ReviewManager {
        public String id;
        public String name;
        public String avatar;
        public Integer status;
        public Integer level;
        public String phone;
        public String wechat;
        public String father;
        public String idCard;
        public Integer payType;
        public String payAccount;
        public List<String> idPics;
    }

    static public class GetAllReviewsRespData{
        public List<ReviewActor> actor;
        public List<ReviewManager> manager;
    }

    public GetAllReviewsRespData VerifyData;


}
