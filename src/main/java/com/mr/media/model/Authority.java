package com.mr.media.model;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by 秦博 on 2017/3/10.
 */

@Entity
@Table(name = "authority")
public class Authority extends Model{

    @Id
    @Column(name = "id")
    int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Admin.class)
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    Admin admin;

    @Column(name = "authority")
    int authority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }
}
