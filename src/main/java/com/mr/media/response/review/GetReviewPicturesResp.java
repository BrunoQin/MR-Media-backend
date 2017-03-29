package com.mr.media.response.review;

import com.mr.media.response.BaseResp;

import java.util.List;

/**
 * Created by tanjingru on 29/03/2017.
 */
public class GetReviewPicturesResp extends BaseResp {
    public List<String> IDPictures;
    public List<String> pictures;

    public GetReviewPicturesResp(int errCode, List<String> IDPictures, List<String> pictures) {
        super(errCode);
        this.IDPictures = IDPictures;
        this.pictures = pictures;
    }

    public GetReviewPicturesResp(int errCode) {
        super(errCode);
    }
}
