package com.mr.media.request.authority.admin;

import java.util.List;

/**
 * Created by tanjingru on 29/03/2017.
 */
public class CreateAdminReq {
    public String uid;
    public String phoneNumber;
    public List<Integer> authorities;

    public CreateAdminReq(String uid, String phoneNumber, List<Integer> authorities) {
        this.uid = uid;
        this.phoneNumber = phoneNumber;
        this.authorities = authorities;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Integer> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Integer> authorities) {
        this.authorities = authorities;
    }
}
