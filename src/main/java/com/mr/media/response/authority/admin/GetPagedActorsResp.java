package com.mr.media.response.authority.admin;

import com.mr.media.response.BaseResp;

import java.util.List;

/**
 * Created by i321273 on 1/6/17.
 */
public class GetPagedActorsResp extends BaseResp {

    public static class Actor{
        public String uid;
        public String realName;
    }

    public int pageId;
    public int pageSize;
    public int totalpage;
    public List<Actor> actors;

    public GetPagedActorsResp(int errCode, int pageId, int pageSize, int totalpage, List<Actor> actors){
        super(errCode);
        this.pageId = pageId;
        this.pageSize = pageSize;
        this.totalpage = totalpage;
        this.actors = actors;
    }
}
