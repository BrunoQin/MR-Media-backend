package com.mr.media.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.PagedList;
import com.mr.media.model.Review;
import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.tool.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 秦博 on 2017/3/11.
 */

@Service
public class ReviewService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    public Review findReviewById(int id){
        return Ebean.find(Review.class).where()
                .eq("id", id).findUnique();
    }

    public Pair<Integer, List<Review>> getPagedReviews(String token, Integer pageId, Integer pageSize, Integer status){

        User receiver = userService.findUserByToken(token);
        if(receiver.getAuthority() != 0){
            return new Pair(new BaseResp(BaseResp.PERMISSION_DENIED), null);
        }
        if(status != Review.READ_REVIEW && status != Review.UNREAD_REVIEW && status != Review.UNMARK){
            return new Pair(new BaseResp(BaseResp.GET_REVIEWS_WRONG_STATUS), null);
        }
        ExpressionList<Review> el = Ebean.find(Review.class).where();
        el = el.ne("action", Review.ACTION_DELETE);
        if(status != null){
            el = el.eq("status", status);
        }
        PagedList<Review> pl = el.findPagedList(pageId, pageSize);
        int totalPages = pl.getTotalPageCount();
        List<Review> reviews = pl.getList();
        return new Pair<>(totalPages, reviews);

    }

    public int markReview(Integer reviewId, Integer status, Integer action){

        Review review = findReviewById(reviewId);
        if(review == null){
            return BaseResp.MARK_REVIEW_NO_RESULT;
        }

        if(status != null){
            if(status != Review.READ_REVIEW && status != Review.UNREAD_REVIEW && status != Review.UNMARK){
                return BaseResp.GET_REVIEWS_WRONG_STATUS;
            }
            review.setStatus(status);
        }

        if(action != null){
            if(action != Review.ACTION_ACCEPT && action != Review.ACTION_DECLINE && action != Review.ACTION_DELETE){
                return BaseResp.MARK_REVIEW_WRONG_TYPE;
            }
            review.setAction(action);
        }

        try{
            review.save();
            return BaseResp.SUCCESS;
        } catch (Exception e){
            logger.error("标记审核错误", e);
            return BaseResp.UNKNOWN;
        }

    }

}
