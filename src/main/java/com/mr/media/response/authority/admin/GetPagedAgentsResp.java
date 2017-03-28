package com.mr.media.response.authority.admin;

import com.mr.media.response.BaseResp;

import java.util.List;

/**
 * Created by i321273 on 1/6/17.
 */
public class GetPagedAgentsResp extends BaseResp {

    public static class Agent{
        public String uid;
        public String realName;
        public String tel;
        public String weChat;
        public Integer level;
        public String parentName;
        public int subNumber;
    }

    public int pageId;
    public int pageSize;
    public int totalpage;
    public List<Agent> agents;

    public GetPagedAgentsResp(int errCode, int pageId, int pageSize, int totalpage, List<Agent> agents){
        super(errCode);
        this.pageId = pageId;
        this.pageSize = pageSize;
        this.totalpage = totalpage;
        this.agents = agents;
    }
}
