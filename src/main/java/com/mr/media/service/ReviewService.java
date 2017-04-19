package com.mr.media.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.PagedList;
import com.mr.media.model.*;
import com.mr.media.request.review.OperateReviewReq;
import com.mr.media.response.BaseResp;
import com.mr.media.response.authority.agent.GetReviewsResp;
import com.mr.media.response.review.GetAllReviewsResp;
import com.mr.media.service.authority.ActorService;
import com.mr.media.service.authority.AgentService;
import com.mr.media.tool.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    private Integer ID_PICTURE_TYPE = 0;
    private Integer PICTURE_TYPE = 1;

    public GetAllReviewsResp getAllReviews(){
        List<Review> reviews = Ebean.find(Review.class).where().findList();
        List<GetAllReviewsResp.ReviewActor> actorReviewEntityList = new ArrayList<>();
        List<GetAllReviewsResp.ReviewManager> agentReviewEntityList = new ArrayList<>();
        for(Review review: reviews){
            if(review.getCreator().getRole() == User.ACTOR_ROLE){
                Actor actor = Ebean.find(Actor.class).where().eq("actor.id", review.getCreator().getId()).findUnique();
                GetAllReviewsResp.ReviewActor reviewActor = new GetAllReviewsResp.ReviewActor();
                reviewActor.id = review.getCreator().getUid();
                reviewActor.avatar = actor.getAvatar();
                reviewActor.father = review.getCreator().getSuperUser().getRealName();
                reviewActor.idCard = actor.getIdNumber();
                reviewActor.location = actor.getLocation();
                reviewActor.name = actor.getRealName();
                reviewActor.payAccount = actor.getSettleAccount();
                reviewActor.payType = actor.getSettleType();
                reviewActor.phone = actor.getPhoneNumber();
                reviewActor.skills = new ArrayList<>();
                reviewActor.status = review.getStatus();
                reviewActor.wechat = actor.getWechatNumber();
                reviewActor.idPics = getPictures(review, ID_PICTURE_TYPE);
                reviewActor.photo = getPictures(review, PICTURE_TYPE);
                actorReviewEntityList.add(reviewActor);
            }
            if(review.getCreator().getRole() == User.AGENT_ROLE){
                Agent agent = Ebean.find(Agent.class).where().eq("agent.id", review.getCreator().getId()).findUnique();
                GetAllReviewsResp.ReviewManager reviewAgent = new GetAllReviewsResp.ReviewManager();
                reviewAgent.id = review.getCreator().getUid();
                reviewAgent.avatar = agent.getAvatar();
                reviewAgent.father = review.getCreator().getSuperUser().getRealName();
                reviewAgent.idCard = agent.getIdNumber();
                reviewAgent.name = agent.getRealName();
                // agent 没有settleAccount
                reviewAgent.payAccount = "";
                // agent 没有payType
                reviewAgent.payType = 0;
                reviewAgent.phone = agent.getPhoneNumber();
                reviewAgent.status = review.getStatus();
                reviewAgent.wechat = agent.getWechatNumber();
                reviewAgent.idPics = getPictures(review, ID_PICTURE_TYPE);
                agentReviewEntityList.add(reviewAgent);
            }
        }
        GetAllReviewsResp.GetAllReviewsRespData data = new GetAllReviewsResp.GetAllReviewsRespData();
        data.actor = actorReviewEntityList;
        data.manager = agentReviewEntityList;
        return new GetAllReviewsResp(BaseResp.SUCCESS, data);
    }


    public GetReviewsResp getReviewByRecommeder(User recommender) {
        List<Review> reviews =  Ebean.find(Review.class).where().eq("recommender", recommender.getId()).findList();
        List<GetReviewsResp.Person> persons = new ArrayList<>();
        for(Review review: reviews){
            if(review.getCreator().getRole() == User.ACTOR_ROLE){
                Actor actor = Ebean.find(Actor.class).where().eq("actor.id", review.getCreator().getId()).findUnique();
                GetReviewsResp.Person person = new GetReviewsResp.Person();
                person.id = review.getCreator().getUid();
                person.type = User.ACTOR_ROLE;
                person.status = review.getStatus();
                person.name = review.getCreator().getRealName();
                person.phone = actor.getPhoneNumber();
                person.wechat = actor.getWechatNumber();
                person.father = review.getCreator().getSuperUser().getRealName();
                person.idCard = actor.getIdNumber();
                persons.add(person);
            }
            if(review.getCreator().getRole() == User.AGENT_ROLE){
                Agent agent = Ebean.find(Agent.class).where().eq("agent.id", review.getCreator().getId()).findUnique();
                GetReviewsResp.Person person = new GetReviewsResp.Person();
                person.id = review.getCreator().getUid();
                person.type = User.AGENT_ROLE;
                person.status = review.getStatus();
                person.name = review.getCreator().getRealName();
                person.phone = agent.getPhoneNumber();
                person.wechat = agent.getWechatNumber();
                person.father = review.getCreator().getSuperUser().getRealName();
                person.idCard = agent.getIdNumber();
                persons.add(person);
            }
        }
        return new GetReviewsResp(BaseResp.SUCCESS, persons);
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
        Ebean.commitTransaction();
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
        Ebean.commitTransaction();
        Ebean.endTransaction();
        return new BaseResp(BaseResp.SUCCESS);
    }

}
