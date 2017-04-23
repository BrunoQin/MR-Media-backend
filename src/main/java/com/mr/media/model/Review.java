package com.mr.media.model;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by 秦博 on 2017/3/10.
 */

@Entity
@Table(name = "review")
public class Review extends Model{

    public static final int READ_REVIEW = 0;
    public static final int UNREAD_REVIEW = 1;
    public static final int UNMARK = 2;

    public static final int ACTION_UNHANDLE = 0;
    public static final int ACTION_ACCEPT = 1;
    public static final int ACTION_DECLINE = 2;
    public static final int ACTION_DELETE = 3;

    @Id
    @Column(name = "id")
    int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    User creator;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "recommend_id", referencedColumnName = "id")
    User recommender;

    @Column(name = "text_content")
    String textContent;

    @Column(name = "status")
    int status;

    @Column(name = "action")
    int action;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getRecommender() {
        return recommender;
    }

    public void setRecommender(User recommender) {
        this.recommender = recommender;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}
