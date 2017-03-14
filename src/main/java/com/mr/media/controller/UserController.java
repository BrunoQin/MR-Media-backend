package com.mr.media.controller;

import com.mr.media.model.User;
import com.mr.media.request.user.ChangePasswordReq;
import com.mr.media.request.user.LoginReq;
import com.mr.media.response.BaseResp;
import com.mr.media.response.user.GetProfileResp;
import com.mr.media.response.user.LoginResp;
import com.mr.media.response.user.SubEmployeeDetailResp;
import com.mr.media.response.user.SubEmployeesResp;
import com.mr.media.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by i321273 on 1/5/17.
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResp login(@RequestBody LoginReq loginReq){

        int errCode = userService.login(loginReq.uid, loginReq.password);
        if(errCode != BaseResp.SUCCESS){
            return new BaseResp(errCode);
        }

        User user = userService.findUserByUid(loginReq.uid);
        return new LoginResp(BaseResp.SUCCESS, user.getToken(), user.getAuthority());

    }

    /**
     * 用户查看个人信息
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public BaseResp getProfile(String token){

        User user = userService.findUserByToken(token);
        GetProfileResp.Profile profile = new GetProfileResp.Profile();
        profile.uid = user.getUid();
        profile.realname = user.getRealName();
        return new GetProfileResp(BaseResp.SUCCESS, profile);

    }

    /**
     * 用户更改密码
     */
    @RequestMapping(value = "/password/edit", method = RequestMethod.POST)
    public BaseResp changePassword(String token, @RequestBody ChangePasswordReq changePasswordReq){
        int errCode = userService.changePassword(token, changePasswordReq.oldPassword, changePasswordReq.newPassword, changePasswordReq.confirmPassword);
        return new BaseResp(errCode);

    }

    /**
     * 用户查看下属员工
     */
    @RequestMapping(value = "/sub_employees", method = RequestMethod.POST)
    public SubEmployeesResp lookUpSubEmployees(String token, Integer pageId, Integer pageSize){
        SubEmployeesResp result =  userService.lookUpSubEmployees(token, pageId, pageSize);
        return result;
    }

    @RequestMapping(value = "/sub_employee/{uid}", method = RequestMethod.POST)
    public SubEmployeeDetailResp getSubEmployeeDetail(String token, @PathVariable String uid){
        return userService.getSubEmployeeDetail(token, uid);
    }

}
