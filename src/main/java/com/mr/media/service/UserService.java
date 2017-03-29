package com.mr.media.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.PagedList;
import com.mr.media.model.Platform;
import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.response.user.LookUpAgentSubEmployeesResp;
import com.mr.media.response.user.SubEmployeeDetailResp;
import com.mr.media.response.user.SubEmployeesResp;
import com.mr.media.service.authority.ActorService;
import com.mr.media.util.TokenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by i321273 on 1/5/17.
 */

@Service
public class UserService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ActorService actorService;

    public User findUserByRealName(String realName){
        return Ebean.find(User.class).where()
                .eq("real_name", realName)
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

    public User findUserByOpenId(String openId){
        return Ebean.find(User.class).where()
                .eq("open_id", openId)
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

    public BaseResp addPlatformForActor(String name, String uid, int validDay, int validHour, int giftCount, int settleCount){
        //容错
        Platform platform = new Platform();

        try{
            platform.setName(name);
            platform.setActor(actorService.findActorByUid(findUserByUid(uid).getId()));
            platform.setValidDay(validDay);
            platform.setValidHour(validHour);
            platform.setGiftCount(giftCount);
            platform.setSettleCount(settleCount);
            platform.save();
            return new BaseResp(BaseResp.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            return  new BaseResp(BaseResp.UNKNOWN);
        }

    }

}
