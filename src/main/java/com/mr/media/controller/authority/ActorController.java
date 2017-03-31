package com.mr.media.controller.authority;

import com.avaje.ebeaninternal.server.deploy.generatedproperty.GeneratedInsertJavaTime;
import com.mr.media.request.authority.actor.ActorEditReq;
import com.mr.media.request.authority.actor.ActorRegisterReq;
import com.mr.media.response.BaseResp;
import com.mr.media.response.authority.actor.UploadAvatarResp;
import com.mr.media.response.authority.actor.UploadVideoResp;
import com.mr.media.response.authority.agent.UploadPictureResp;
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

//    /**
//     * 艺人上传个人照片
//     */
//    @RequestMapping(value = "/upload_file", method = RequestMethod.POST)
//    public BaseResp uploadFile(String token, MultipartFile file) throws IOException {
//        UploadAvatarResp resp = actorService.UploadAvatar(token, file);
//        return resp;
//    }
//
//    @RequestMapping(value = "/upload_video", method = RequestMethod.POST)
//    public BaseResp uploadVideo(String token, MultipartFile file) throws IOException {
//        UploadVideoResp resp = actorService.uploadVideo(token, file);
//        return resp;
//    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseResp actorRegister(@RequestBody ActorRegisterReq actorRegisterReq){
        int errCode = actorService.actorRegister(actorRegisterReq.uid, actorRegisterReq.realName, actorRegisterReq.avatar, actorRegisterReq.talentType, actorRegisterReq.phoneNumber, actorRegisterReq.weChatNumber, actorRegisterReq.email, actorRegisterReq.location, actorRegisterReq.settleType, actorRegisterReq.settleAccount, actorRegisterReq.idNumber);
        return new BaseResp(errCode);
    }

    @RequestMapping(value = "/edit/{uid}", method = RequestMethod.POST)
    public BaseResp actorEdit(@PathVariable String uid, @RequestBody ActorEditReq actorEditReq){

        int errCode = actorService.actorEdit(uid, actorEditReq.realName, actorEditReq.active, actorEditReq.level, actorEditReq.phoneNumber, actorEditReq.weChatNumber, actorEditReq.parentUid, actorEditReq.location, actorEditReq.talentType, actorEditReq.settleType, actorEditReq.settleAccount, actorEditReq.idNumber);

        return new BaseResp(errCode);

    }

//    @RequestMapping(value = "/register/upload_pictures", method = RequestMethod.POST)
//    public UploadPictureResp actorUploadPictures(String token, MultipartFile picture1, MultipartFile picture2, MultipartFile picture3){
//        return actorService.uploadPictures(token, picture1, picture2, picture3);
//    }

}
