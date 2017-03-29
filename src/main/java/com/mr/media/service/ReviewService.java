package com.mr.media.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.PagedList;
import com.mr.media.model.*;
import com.mr.media.request.review.OperateReviewReq;
import com.mr.media.response.BaseResp;
import com.mr.media.response.review.GetAllReviewsResp;
import com.mr.media.response.review.GetReviewPicturesResp;
import com.mr.media.service.authority.ActorService;
import com.mr.media.service.authority.AgentService;
import com.mr.media.tool.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 秦博 on 2017/3/11.
 */

@Service
public class ReviewService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @Autowired
    AgentService agentService;

    @Autowired
    ActorService actorService;

    public Review findReviewById(int id){
        return Ebean.find(Review.class).where()
                .eq("id", id).findUnique();
    }

    public Pair<Integer, List<Review>> getPagedReviews(String token, Integer pageId, Integer pageSize, Integer status){

        User receiver = userService.findUserByToken(token);
        if(receiver.getRole() != User.ADMIN_ROLE){
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

    public GetAllReviewsResp getAllReviews(User recommender){
        List<Review> reviews;
        if(recommender != null){
             reviews =  Ebean.find(Review.class).where().eq("recommend_id", recommender.getId()).findList();
        }
        else{
            reviews =  Ebean.find(Review.class).where().findList();
        }
        List<GetAllReviewsResp.ReviewEntity> reviewEntityList = new ArrayList<>();
        for(Review review: reviews){
            reviewEntityList.add(new GetAllReviewsResp.ReviewEntity(review,
                    getPictures(review, 0),
                    getPictures(review, 1)));
        }
        return new GetAllReviewsResp(BaseResp.SUCCESS, reviewEntityList);
    }


    private String getIDNumber(Review review){
        User user = review.getCreator();
        String IDNumber = "";
        if(user.getRole() == 2){
            IDNumber =  Ebean.find(Agent.class).where().eq("uid.id", user.getId()).findUnique().getIdNumber();
        }
        if(user.getRole() == 3){
            IDNumber =  Ebean.find(Actor.class).where().eq("uid.id", user.getId()).findUnique().getIdNumber();
        }
        return IDNumber;
    }



    private List<String> getPictures(Review review, int type){
        return Ebean.find(Picture.class).where().eq("owner_id.id", review.getCreator().getId()).eq("type", type).findList()
                .stream().map(Picture::getLocation
                ).collect(Collectors.toList());
    }



    public BaseResp deleteReview(String rid) {
        Ebean.beginTransaction();
        Review review = Ebean.find(Review.class).where().eq("id", rid).findUnique();
        if(review == null){
            return new BaseResp(BaseResp.RESOURCES_NOT_EXIST);
        }
        review.delete();
        Ebean.endTransaction();
        return new BaseResp(BaseResp.SUCCESS);
    }


    public BaseResp operateReview(String rid, OperateReviewReq operation) {
        Ebean.beginTransaction();
        Review review = Ebean.find(Review.class).where().eq("id", rid).findUnique();
        if(review == null){
            return new BaseResp(BaseResp.RESOURCES_NOT_EXIST);
        }
        if(operation.operation == 0)
        {
            review.setStatus(1);
        }

        if(operation.operation == 1){
            review.setStatus(2);
        }
        review.update();
        Ebean.endTransaction();
        return new BaseResp(BaseResp.SUCCESS);
    }

}
