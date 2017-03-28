package com.mr.media.service.authority;

import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.response.authority.actor.UploadAvatarResp;
import com.mr.media.response.authority.actor.UploadVideoResp;
import com.mr.media.response.authority.agent.UploadPictureResp;
import com.mr.media.service.UploadService;
import com.mr.media.service.UserService;
import com.mr.media.util.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by tanjingru on 11/01/2017.
 */
@Service
public class ActorService {

    @Autowired
    UserService userService;

    @Autowired
    FileHelper fileHelper;

    @Autowired
    UploadService uploadService;

    final Logger logger = LoggerFactory.getLogger(getClass());

    public UploadAvatarResp UploadAvatar(String token, MultipartFile uploadFile){
        return uploadService.upLoadAvatar(token, uploadFile);
    }

    public UploadVideoResp uploadVideo(String token, MultipartFile uploadFile) {
        return uploadService.uploadVideo(token, uploadFile);
    }

    public int actorRegister(String uid, String realName, Integer telentType, String phoneNumber, String weChatNumber, String email, String location, Integer settleType, String settleAccount){

        User user = userService.findUserByUid(uid);
        if(user != null) {
            return BaseResp.AGENT_REGISTER_EXIST_UID;
        }
        // 创建通知以及审核×××××待完成
        // 找到超级管理员，以后要改
        User superAdmin = userService.findUserByUid("ddd");

        user = new User();
        user.setUid(uid);
        user.setRealName(realName);
        user.setTalentType(telentType);
        user.setPhoneNumber(phoneNumber);
        user.setWechatNumber(weChatNumber);
        user.setEmail(email);
        user.setLocation(location);
        user.setSettleType(settleType);
        user.setSettleAccount(settleAccount);
        user.setAuthority(999);
        user.setLevel(2);
        user.setSuperUser(superAdmin);

        try{
            user.save();
            return BaseResp.SUCCESS;
        } catch (Exception e) {
            logger.error("failed to register actor", e);
            return BaseResp.UNKNOWN;
        }

    }

    public UploadPictureResp upLoadPicture(String token, MultipartFile multipartFile){
        return uploadService.UploadPicture(token, multipartFile);
    }

    public UploadPictureResp uploadPictures(String token, MultipartFile picture1, MultipartFile picture2, MultipartFile picture3) {
        UploadPictureResp result1 = upLoadPicture(token, picture1);
        UploadPictureResp result2 = upLoadPicture(token, picture2);
        UploadPictureResp result3 = upLoadPicture(token, picture3);
        int error = 0;
        if(result1.errCode != BaseResp.SUCCESS) error = result1.errCode;
        if(result2.errCode != BaseResp.SUCCESS) error = result2.errCode;
        if(result3.errCode != BaseResp.SUCCESS) error = result3.errCode;
        return new UploadPictureResp(error);
    }
}
