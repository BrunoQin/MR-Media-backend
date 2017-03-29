package com.mr.media.controller;

import com.mr.media.model.User;
import com.mr.media.request.user.AddPlatformForActorReq;
import com.mr.media.request.user.ChangePasswordReq;
import com.mr.media.request.user.LoginReq;
import com.mr.media.request.user.LookUpAgentSubEmployeesReq;
import com.mr.media.response.BaseResp;
import com.mr.media.response.user.*;
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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "hello";
    }

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
        return new LoginResp(BaseResp.SUCCESS, user.getToken(), user.getRole());

    }

//    /**
//     * 用户查看个人信息
//     */
//    @RequestMapping(value = "/profile", method = RequestMethod.GET)
//    public BaseResp getProfile(String token){
//
//        User user = userService.findUserByToken(token);
//        GetProfileResp.Profile profile = new GetProfileResp.Profile();
//        profile.uid = user.getUid();
//        profile.realname = user.getRealName();
//        return new GetProfileResp(BaseResp.SUCCESS, profile);
//
//    }

    /**
     * 用户更改密码
     */
    @RequestMapping(value = "/password/edit", method = RequestMethod.POST)
    public BaseResp changePassword(String token, @RequestBody ChangePasswordReq changePasswordReq){
        int errCode = userService.changePassword(token, changePasswordReq.oldPassword, changePasswordReq.newPassword, changePasswordReq.confirmPassword);
        return new BaseResp(errCode);

    }

    /**
     * 用户查看下属员工详细信息
     */
    @RequestMapping(value = "/sub_employee/{uid}", method = RequestMethod.POST)
    public LookUpSubEmployeeDetailResp getSubEmployeeDetail(String token, @PathVariable String uid){
        return userService.lookUpSubEmployeeDetail(token, uid);
    }

    /**
     * 用户查看其他员工下属员工
     */
    @RequestMapping(value = "/{uid}/sub_employees", method = RequestMethod.POST)
    public BaseResp lookUpAgentSubEmployees(@PathVariable String uid, @RequestBody LookUpAgentSubEmployeesReq lookUpAgentSubEmployeesReq){

        return userService.lookUpAgentSubEmployees(uid, lookUpAgentSubEmployeesReq.authority);

    }

    @RequestMapping(value = "/add_platform", method = RequestMethod.POST)
    public BaseResp addPlatformForActor(@RequestBody AddPlatformForActorReq addPlatformForActorReq){

        return userService.addPlatformForActor(addPlatformForActorReq.name, addPlatformForActorReq.uid, addPlatformForActorReq.validDay, addPlatformForActorReq.validHour, addPlatformForActorReq.giftCount, addPlatformForActorReq.settleCount);

    }

}
