package com.mr.media.service.authority;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.PagedList;
import com.mr.media.model.Admin;
import com.mr.media.model.Agent;
import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.tool.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by i321273 on 1/6/17.
 */

@Service
public class AdminService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    public Admin findAdminByUid(int uid){
        return Ebean.find(Admin.class).where()
                .eq("uid", uid)
                .findUnique();
    }

    public Pair<Integer, List<User>> getPagedEmployees(Integer pageId, Integer pageSize, Integer role, Integer disable){

        if(pageId < 0 || pageSize <= 0){
            return new Pair(new BaseResp(BaseResp.WRONG_PAGE_PARAM), null);
        }

        ExpressionList<User> el = Ebean.find(User.class).where();

        el = el.eq("role", role);
        if(disable != null){
            el = el.eq("disable", disable);
        }

        PagedList<User> pl = el.findPagedList(pageId, pageSize);

        int totalPages = pl.getTotalPageCount();
        List<User> users = pl.getList();
        return new Pair<>(totalPages, users);

    }

    public int getSubActorsNumber(String superId){

        return Ebean.find(User.class).where()
                .eq("super_id", superId)
                .eq("role", User.ACTOR_ROLE)
                .findRowCount();

    }

}
