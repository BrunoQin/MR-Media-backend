package com.mr.media.response.authority.actor;

import com.mr.media.response.BaseResp;

/**
 * Created by tanjingru on 12/01/2017.
 */
public class UploadVideoResp extends BaseResp {

    public Location location;

    public UploadVideoResp(int errCode, Location location) {
        super(errCode);
        this.location = location;
    }

    public static class Location {
        public String filename;
        public Location(String filename){
            this.filename = filename;
        }
    }

}
