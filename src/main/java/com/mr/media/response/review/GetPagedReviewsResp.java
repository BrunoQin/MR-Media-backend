package com.mr.media.response.review;

import com.mr.media.model.User;
import com.mr.media.response.BaseResp;

import java.util.List;

/**
 * Created by 秦博 on 2017/3/11.
 */
public class GetPagedReviewsResp extends BaseResp{

    public static class Review{
        public Integer reviewId;
        public String content;
        public User Creator;
        public User Recommender;
        public Integer status;
        public Integer action;
    }

    public int pageId;
    public int pageSize;
    public int totalpage;
    public List<Review> reviews;

    public GetPagedReviewsResp(int errCode, int pageId, int pageSize, int totalpage, List<Review> reviews) {
        super(errCode);
        this.pageId = pageId;
        this.pageSize = pageSize;
        this.totalpage = totalpage;
        this.reviews = reviews;
    }
}
