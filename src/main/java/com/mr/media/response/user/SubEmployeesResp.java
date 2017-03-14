package com.mr.media.response.user;

import com.mr.media.model.User;
import com.mr.media.response.BaseResp;

import java.util.List;

/**
 * Created by tanjingru on 13/03/2017.
 */
public class SubEmployeesResp extends BaseResp{
    public Integer pageId;
    public Integer pageSize;
    public Integer totalPage;
    public List<User> subEmployees;

    public SubEmployeesResp(int errCode, Integer pageId, Integer pageSize, Integer totalPage, List<User> subEmployees) {
        super(errCode);
        this.pageId = pageId;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.subEmployees = subEmployees;
    }

    public SubEmployeesResp(int errCode) {
        super(errCode);
    }
}
