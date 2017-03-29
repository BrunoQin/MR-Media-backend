package com.mr.media.model;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by 秦博 on 2017/3/28.
 */

@Entity
@Table(name = "platform")
public class Platform extends Model {

    @Id
    @Column(name = "id")
    int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Actor.class)
    @JoinColumn(name = "actor_id", referencedColumnName = "id")
    Actor actor;

    @Column(name = "valid_day")
    int validDay;

    @Column(name = "valid_hour")
    int validHour;

    @Column(name = "gift_count")
    int giftCount;

    @Column(name = "settle_count")
    int settleCount;

    @Column(name = "name")
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public int getValidDay() {
        return validDay;
    }

    public void setValidDay(int validDay) {
        this.validDay = validDay;
    }

    public int getValidHour() {
        return validHour;
    }

    public void setValidHour(int validHour) {
        this.validHour = validHour;
    }

    public int getGiftCount() {
        return giftCount;
    }

    public void setGiftCount(int giftCount) {
        this.giftCount = giftCount;
    }

    public int getSettleCount() {
        return settleCount;
    }

    public void setSettleCount(int settleCount) {
        this.settleCount = settleCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
