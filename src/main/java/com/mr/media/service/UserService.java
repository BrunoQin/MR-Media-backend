package com.mr.media.service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.PagedList;
import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.response.user.SubEmployeeDetailResp;
import com.mr.media.response.user.SubEmployeesResp;
import com.mr.media.util.TokenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by i321273 on 1/5/17.
 */

@Service
public class UserService {

    final Logger logger = LoggerFactory.getLogger(getClass());

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

    public SubEmployeesResp lookUpSubEmployees(String token, Integer pageId, Integer pageSize) {
        User user = findUserByToken(token);
        PagedList<User> pl =  Ebean.find(User.class).findPagedList(pageId, pageSize);
        int totalPage = pl.getTotalPageCount();
        return new SubEmployeesResp(0, pageId, pageSize, totalPage, pl.getList());
    }

    public SubEmployeeDetailResp getSubEmployeeDetail(String token, String uid) {
        User employee = findUserByUid(uid);
        if(employee == null) return new SubEmployeeDetailResp(BaseResp.USER_IS_NOT_EXIST, null);
        User inspector = findUserByToken(token);
        if(employee.getUid().equals(inspector.getUid())) return new SubEmployeeDetailResp(BaseResp.SUCCESS, employee);
        User parent = employee.getSuperUser();
        // 向上递归搜索
        while(true){
            if(parent == null) break;
            if(inspector.getUid().equals(parent.getUid())) return new SubEmployeeDetailResp(BaseResp.SUCCESS, employee);
            parent = parent.getSuperUser();
        }
        return new SubEmployeeDetailResp(BaseResp.PERMISSION_DENIED, null);
    }
}
