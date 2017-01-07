package com.mr.media.response.authority.agent;

import com.avaje.ebeaninternal.server.lib.util.Str;
import com.mr.media.model.User;
import com.mr.media.response.BaseResp;

import java.util.List;

/**
 * Created by tanjingru on 07/01/2017.
 */
public class PositionResp extends BaseResp {

    public Position position;

    public PositionResp(int errCode, Position position) {
        super(errCode);
        this.position = position;
    }

    public static class UserNode {
        public long uid;
        public String username;
        public int positionIndex;
        public UserNode(long uid, String username, int positionIndex){
            this.uid = uid;
            this.username = username;
            this.positionIndex = positionIndex;
        }
        public UserNode(){

        }

    }

    public static class Position {
        public UserNode parent;
        public List<UserNode> children;
    }


}
