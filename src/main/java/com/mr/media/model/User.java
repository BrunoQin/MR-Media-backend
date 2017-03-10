package com.mr.media.model;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by i321273 on 1/5/17.
 */

@Entity
@Table(name = "user")
public class User extends Model {

    public final static int ADMIN_AUTHORITY = 1;
    public final static int AGENT_AUTHORITY = 2;
    public final static int ACTOR_AUTHORITY = 3;

    public final static String DEFAULT_PWD = "password";

    public final static Integer USER_ACTIVE = 0;
    public final static Integer USER_DEACTIVE = 1;

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "uid")
    String uid;

    @Column(name = "real_name")
    String realName;

    @Column(name = "password")
    String password;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "authority")
    int authority;

    @Column(name = "level")
    int level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "super_id")
    User superUser;

    @Column(name = "disable")
    int disable;

    @Column(name = "token")
    String token;

    @Column(name = "valid_time")
    Date validTime;

    @Column(name = "email")
    String email;

    @Column(name = "talent_type")
    int talentType;

    @Column(name = "location")
    String location;

    @Column(name = "settle_type")
    int settleType;

    @Column(name = "settle_account")
    String settleAccount;

    @Column(name = "active")
    int active;

    @Column(name = "open_id")
    String openId;

    @Column(name = "wechat_number")
    String wechatNumber;

    @Column(name = "phone_number")
    String phoneNumber;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public User getSuperUser() {
        return superUser;
    }

    public void setSuperUser(User superUser) {
        this.superUser = superUser;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTalentType() {
        return talentType;
    }

    public void setTalentType(int talentType) {
        this.talentType = talentType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSettleType() {
        return settleType;
    }

    public void setSettleType(int settleType) {
        this.settleType = settleType;
    }

    public String getSettleAccount() {
        return settleAccount;
    }

    public void setSettleAccount(String settleAccount) {
        this.settleAccount = settleAccount;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getWechatNumber() {
        return wechatNumber;
    }

    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
