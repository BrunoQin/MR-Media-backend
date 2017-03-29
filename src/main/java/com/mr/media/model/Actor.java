package com.mr.media.model;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by 秦博 on 2017/3/29.
 */

@Entity
@Table(name = "actor")
public class Actor extends Model{

    public final static Integer ACTOR_ONLINE = 0;
    public final static Integer ACTOR_OFFLINE = 1;

    @Id
    @Column(name = "id")
    int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "uid", referencedColumnName = "id")
    User actor;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "active")
    int active;

    @Column(name = "wechat_number")
    String wechatNumber;

    @Column(name = "id_number")
    String idNumber;

    @Column(name = "talent_type")
    int talentType;

    @Column(name = "settle_type")
    int settleType;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "location")
    String location;

    @Column(name = "email")
    String email;

    @Column(name = "settle_account")
    String settleAccount;

    @Column(name = "real_name")
    String realName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getActor() {
        return actor;
    }

    public void setActor(User actor) {
        this.actor = actor;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getWechatNumber() {
        return wechatNumber;
    }

    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getTalentType() {
        return talentType;
    }

    public void setTalentType(int talentType) {
        this.talentType = talentType;
    }

    public int getSettleType() {
        return settleType;
    }

    public void setSettleType(int settleType) {
        this.settleType = settleType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSettleAccount() {
        return settleAccount;
    }

    public void setSettleAccount(String settleAccount) {
        this.settleAccount = settleAccount;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
