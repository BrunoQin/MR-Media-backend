package com.mr.media.service;

import com.avaje.ebean.Ebean;
import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.util.FileHelper;
import com.mr.media.util.TokenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;

/**
 * Created by i321273 on 1/5/17.
 */

@Service
public class UserService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    Environment environment;

    public User findUserByUsername(String username){
        return Ebean.find(User.class).where()
                .eq("username", username)
                .findUnique();
    }

    public User findUserByUid(String uid){
        return Ebean.find(User.class).where()
                .eq("uid", uid)
                .findUnique();
    }

    public User findUserByToken(String token){
        return Ebean.find(User.class).where()
                .eq("token", token)
                .findUnique();
    }

    public int login(String uid, String password){

        User user = Ebean.find(User.class).where()
                .eq("uid", uid)
                .eq("password", password)
                .findUnique();

        if(user == null){
            return BaseResp.PASSWORD_NOT_MATCH;
        }

        if(user.getDisable() == User.USER_DEACTIVE){
            return BaseResp.USER_DEACTIVE;
        }

        user.setToken(TokenHelper.newToken(uid));
        user.setValidTime(TokenHelper.newTokenValidTime());
        try {
            user.update();
            return BaseResp.SUCCESS;
        } catch (Exception e) {
            logger.error("用户登录失败", e);
            return BaseResp.UNKNOWN;
        }
    }

    public int changePassword(String token, String oldPassword, String newPassword, String confirmPassword){
        User user = findUserByToken(token);
        if(!user.getPassword().equals(oldPassword)){
            return BaseResp.WRONG_OLD_PASSWORD;
        }
        if(!newPassword.equals(confirmPassword)){
            return BaseResp.CONFIRM_NOT_MATCH;
        }
        if(oldPassword.equals(newPassword)){
            return BaseResp.NEW_AND_OLD_SAME;
        }

        user.setPassword(newPassword);
        try{
            user.update();
            return BaseResp.SUCCESS;
        } catch (Exception e){
            logger.error("修改密码错误", e);
            return BaseResp.UNKNOWN;
        }
    }

     public int UploadAvatar(String token, MultipartFile uploadFile){
             User user = findUserByToken(token);
             String filename = FileHelper.generateName(uploadFile.getOriginalFilename(), user.getUid());
             String directory = environment.getProperty("mr.media.paths.uploadedFiles");
             String filepath = Paths.get(directory, filename).toString();
         try {

             Boolean result = FileHelper.saveFile(filepath, uploadFile.getBytes());
             if(result){
                 return BaseResp.SUCCESS;
             }
             else {
                 return BaseResp.UNKNOWN;
             }
         } catch (IOException e) {
             return BaseResp.UNKNOWN;
         }
     }

}
