package com.mr.media.service.authority;

import com.google.common.io.Files;
import com.mr.media.model.ActorVideo;
import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.response.authority.actor.UploadAvatarResp;
import com.mr.media.response.authority.actor.UploadVideoResp;
import com.mr.media.service.UserService;
import com.mr.media.util.FileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by tanjingru on 11/01/2017.
 */
@Service
public class ActorService {

    @Autowired
    UserService userService;

    @Autowired
    FileHelper fileHelper;

    public UploadAvatarResp UploadAvatar(String token, MultipartFile uploadFile){
        User user = userService.findUserByToken(token);
        if(!validateAvatar(uploadFile.getOriginalFilename())){
            return new UploadAvatarResp(BaseResp.UPLOAD_FILE_TYPE_NOT_ALLOW, null);
        }
        String filename = fileHelper.generateFilename(uploadFile.getOriginalFilename(), user.getUid());
        String filePath = fileHelper.generateFilePath(filename);
        try {
            Boolean result = fileHelper.saveFile(filePath, uploadFile.getBytes());
            if(result){
                user.setAvatar(filename);
                user.update();
                return new UploadAvatarResp(BaseResp.SUCCESS, new UploadAvatarResp.Location(filename));
            }
            else {
                return new UploadAvatarResp(BaseResp.UNKNOWN, null);
            }
        } catch (IOException e) {
            return new UploadAvatarResp(BaseResp.UNKNOWN, null);
        }
    }

    private Boolean validateAvatar(String filename){
        String extensionName = Files.getFileExtension(filename);
        return fileHelper.validateAvatarType(extensionName);
    }

    private Boolean validateVideo(String filename){
        String extensionName = Files.getFileExtension(filename);
        return fileHelper.validateVideoType(extensionName);
    }



    public UploadVideoResp uploadVideo(String token, MultipartFile uploadFile) {
        User user = userService.findUserByToken(token);
        if(!validateVideo(uploadFile.getOriginalFilename())){
            return new UploadVideoResp(BaseResp.UPLOAD_FILE_TYPE_NOT_ALLOW, null);
        }
        String filename = fileHelper.generateFilename(uploadFile.getOriginalFilename(), user.getUid());
        String filePath = fileHelper.generateFilePath(filename);
        try {
            Boolean result = fileHelper.saveFile(filePath, uploadFile.getBytes());
            if(result){
                ActorVideo actorVideo = new ActorVideo();
                actorVideo.setOwner(user);
                actorVideo.setLocation(filename);
                actorVideo.save();
                return new UploadVideoResp(BaseResp.SUCCESS, new UploadVideoResp.Location(filename));
            }
            else {
                return new UploadVideoResp(BaseResp.UNKNOWN, null);
            }
        }
        catch(IOException e) {
            return new UploadVideoResp(BaseResp.UNKNOWN, null);
        }

    }
}
