package com.mr.media.model;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by 秦博 on 2017/3/29.
 */

@Entity
@Table(name = "agent")
public class Agent extends Model{

    @Id
    @Column(name = "id")
    int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "uid", referencedColumnName = "id")
    User agent;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "wechat_number")
    String wechatNumber;

    @Column(name = "id_number")
    String idNumber;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "email")
    String email;

    @Column(name = "real_name")
    String realName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAgent() {
        return agent;
    }

    public void setAgent(User agent) {
        this.agent = agent;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
