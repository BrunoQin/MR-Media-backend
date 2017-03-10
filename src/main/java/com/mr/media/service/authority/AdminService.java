package com.mr.media.service.authority;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.PagedList;
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

    public Pair<Integer, List<User>> getPagedEmployees(Integer pageId, Integer pageSize, Integer authority, Integer disable, String realName){

        if(pageId < 0 || pageSize <= 0){
            return new Pair(new BaseResp(BaseResp.WRONG_PAGE_PARAM), null);
        }

        ExpressionList<User> el = Ebean.find(User.class).where();

        el = el.eq("authority", authority);
        if(disable != null){
            el = el.eq("disable", disable);
        }
        if(!StringUtils.isEmpty(realName)){
            el = el.contains("real_name", realName);
        }

        PagedList<User> pl = el.findPagedList(pageId, pageSize);

        int totalPages = pl.getTotalPageCount();
        List<User> users = pl.getList();
        return new Pair<>(totalPages, users);

    }

}
