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
        public Integer active;
        public String tel;
        public String weChat;
        public Integer level;
        public String parentName;
        public String idNumber;
        public String location;
    }

    public int pageId;
    public int pageSize;
    public int totalPage;
    public List<Actor> actors;

    public GetPagedActorsResp(int errCode, int pageId, int pageSize, int totalPage, List<Actor> actors){
        super(errCode);
        this.pageId = pageId;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.actors = actors;
    }
}
