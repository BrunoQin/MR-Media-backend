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

    public final static int ADMIN_ROLE = 1;
    public final static int AGENT_ROLE = 2;
    public final static int ACTOR_ROLE = 3;

    public final static String DEFAULT_PWD = "password";

    public final static Integer USER_ACTIVE = 0;
    public final static Integer USER_DEACTIVE = 1;

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "uid")
    String uid;

    @Column(name = "password")
    String password;

    @Column(name = "level")
    int level;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "super_id", referencedColumnName = "id")
    User superUser;

    @Column(name = "disable")
    int disable;

    @Column(name = "token")
    String token;

    @Column(name = "valid_time")
    Date validTime;

    @Column(name = "open_id")
    String openId;

    @Column(name = "role")
    int role;

    @Column(name = "real_name")
    String realName;

    @Column(name = "id_number")
    String idNumber;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getId() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
