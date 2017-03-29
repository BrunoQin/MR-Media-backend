package com.mr.media.response.review;

import com.mr.media.model.Review;
import com.mr.media.response.BaseResp;

import java.util.List;

/**
 * Created by tanjingru on 29/03/2017.
 */

public class GetAllReviewsResp extends BaseResp{

    public GetAllReviewsResp(int errCode, List<ReviewEntity> reviewEntities) {
        super(errCode);
        this.reviewEntities = reviewEntities;
    }

    static public class ReviewEntity{
        public Review review;
        public List<String> idPictures;
        public List<String> pictures;

        public ReviewEntity(Review review, List<String> idPictures, List<String> pictures) {
            this.review = review;
            this.idPictures = idPictures;
            this.pictures = pictures;
        }
    }

    public List<ReviewEntity> reviewEntities;

}
