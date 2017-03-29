package com.mr.media.model;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by 秦博 on 2017/3/29.
 */

@Entity
@Table(name = "admin")
public class Admin extends Model{

    @Id
    @Column(name = "id")
    int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "uid", referencedColumnName = "id")
    User admin;

    @Column(name = "phone_number")
    String phoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
