package com.mr.media.model;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by i321273 on 1/5/17.
 */

@Entity
@Table(name = "User")
public class User extends Model {

    @Id
    @Column(name = "id")
    int id;

    @Column(name = "uid")
    String uid;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
