package com.mr.media.controller;

import com.mr.media.model.Review;
import com.mr.media.request.review.MarkReviewReq;
import com.mr.media.request.review.OperateReviewReq;
import com.mr.media.response.BaseResp;
import com.mr.media.response.review.GetAllReviewsResp;
import com.mr.media.response.review.GetPagedReviewsResp;
import com.mr.media.response.review.GetReviewPicturesResp;
import com.mr.media.service.ReviewService;
import com.mr.media.tool.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 秦博 on 2017/3/11.
 */

@RestController
@RequestMapping(value = "/review")
public class ReviewController {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ReviewService reviewService;


    @RequestMapping(value = "/get_reviews", method = RequestMethod.GET)
    public BaseResp getPagedReviews(String token, Integer pageId, Integer pageSize, Integer status){

        Pair<Integer, List<Review>> pair = reviewService.getPagedReviews(token, pageId, pageSize, status);

        List<GetPagedReviewsResp.Review> reviews = pair.getSecond().stream().map(
                o -> {
                    GetPagedReviewsResp.Review review = new GetPagedReviewsResp.Review();
                    review.reviewId = o.getId();
                    review.content = o.getTextContent();
                    review.Creator = o.getCreator();
                    review.Recommender = o.getRecommender();
                    review.status = o.getStatus();
                    review.action = o.getAction();
                    return review;
                }
        ).collect(Collectors.toList());
        return new GetPagedReviewsResp(BaseResp.SUCCESS, pageId, pageSize, pair.getFirst(), reviews);
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public BaseResp getAllReviews(String token){
        return reviewService.getAllReviews();
    }

    @RequestMapping(value = "/{rid}/delete", method = RequestMethod.POST)
    public BaseResp deleteReview(@PathVariable String rid){
        return reviewService.deleteReview(rid);
    }


    @RequestMapping(value = "/{rid}/operate", method = RequestMethod.POST)
    public BaseResp operateReview(@PathVariable String rid, @RequestBody OperateReviewReq operation){
        return reviewService.operateReview(rid, operation);
    }

    @RequestMapping(value = "/mark_review", method = RequestMethod.POST)
    public BaseResp markReview(@RequestBody MarkReviewReq markReviewReq){

        int errCode = reviewService.markReview(markReviewReq.reviewId, markReviewReq.status, markReviewReq.action);
        return new BaseResp(errCode);

    }

}
