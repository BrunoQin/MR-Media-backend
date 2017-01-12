package com.mr.media.controller.authority;

import com.mr.media.response.BaseResp;
import com.mr.media.response.authority.actor.UploadAvatarResp;
import com.mr.media.response.authority.actor.UploadVideoResp;
import com.mr.media.service.authority.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by tanjingru on 11/01/2017.
 */

@RestController
@RequestMapping(value = "/actor")
public class ActorController {

    @Autowired
    ActorService actorService;

    /**
     * 艺人上传个人照片
     */
    @RequestMapping(value = "/upload_file", method = RequestMethod.POST)
    public BaseResp uploadFile(String token, MultipartFile file) throws IOException {
        UploadAvatarResp resp = actorService.UploadAvatar(token, file);
        return resp;
    }

    @RequestMapping(value = "/upload_video", method = RequestMethod.POST)
    public BaseResp uploadVideo(String token, MultipartFile file) throws IOException {
        UploadVideoResp resp = actorService.uploadVideo(token, file);
        return resp;
    }
}
