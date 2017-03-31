package com.mr.media.response.review;

import com.mr.media.model.Actor;
import com.mr.media.model.Agent;
import com.mr.media.model.Review;
import com.mr.media.response.BaseResp;

import java.util.List;

/**
 * Created by tanjingru on 29/03/2017.
 */

public class GetAllReviewsResp extends BaseResp{

    public GetAllReviewsResp(int errCode, List<ActorReviewEntity> actorReviewEntities, List<AgentReviewEntity> agentReviewEntities) {
        super(errCode);
        this.actorReviewEntities = actorReviewEntities;
        this.agentReviewEntities = agentReviewEntities;
    }

    static public class ActorReviewEntity {
        public Review review;
        public List<String> idPictures;
        public List<String> pictures;
        public Actor actor;

        public ActorReviewEntity(Review review, List<String> idPictures, List<String> pictures, Actor actor) {
            this.review = review;
            this.idPictures = idPictures;
            this.pictures = pictures;
            this.actor = actor;
        }
    }

    static public class AgentReviewEntity {
        public Review review;
        public Agent agent;
        public List<String> idPictures;
        public List<String> pictures;

        public AgentReviewEntity(Review review, List<String> idPictures, List<String> pictures, Agent agent) {
            this.review = review;
            this.agent = agent;
            this.idPictures = idPictures;
            this.pictures = pictures;
        }
    }

    public List<ActorReviewEntity> actorReviewEntities;
    public List<AgentReviewEntity> agentReviewEntities;

}
