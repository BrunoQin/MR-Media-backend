package com.mr.media.model;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 秦博 on 2017/3/29.
 */

@Entity
@Table(name = "settle")
public class Settle extends Model{

    @Id
    @Column(name = "id")
    int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Actor.class)
    @JoinColumn(name = "actor_id", referencedColumnName = "id")
    Actor actor;

    //所在平台的名字
    @Column(name = "platform")
    String platform;

    //主播在平台上面的名字
    @Column(name = "platform_name")
    String platformName;

    //主播在平台上面的ID
    @Column(name = "platform_id")
    String platformId;

    @Column(name = "date")
    Date date;

    @Column(name = "amount")
    int amount;

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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
