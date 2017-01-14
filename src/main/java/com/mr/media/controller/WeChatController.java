package com.mr.media.controller;

import com.mr.media.model.User;
import com.mr.media.request.weChat.BindingReq;
import com.mr.media.response.BaseResp;
import com.mr.media.response.weChat.CheckResp;
import com.mr.media.service.UserService;
import com.mr.media.service.WeChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by i321273 on 1/9/17.
 */

@RestController
@RequestMapping(value = "/WeChat")
public class WeChatController {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    WeChatService weChatService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/check_signature", method = RequestMethod.GET)
    public String weChatCheckSignature(String signature, String timestamp, String nonce, String echostr){

        if(weChatService.weChatCheckSignature(signature, timestamp, nonce)){
            return echostr;
        } else {
            return signature + "_" + timestamp + "_" + nonce;
        }

    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public BaseResp check(String code, String state){

        String openId = weChatService.getOpenIdByCode(code);
        if(openId != null){
            User user = userService.findUserByOpenId(openId);
            if(user != null){
                return new CheckResp(BaseResp.SUCCESS, openId);
            } else {
                return new CheckResp(BaseResp.USER_WECHAT_NOT_BINDING, openId);
            }
        } else {
            return new BaseResp(BaseResp.GET_USER_OPENID_ERROR);
        }

    }

    @RequestMapping(value = "/binding", method = RequestMethod.POST)
    public BaseResp binding(@RequestBody BindingReq bindingReq){

        int errCode = userService.login(bindingReq.username, bindingReq.password);
        if(errCode == BaseResp.SUCCESS){
            User user = userService.findUserByUsername(bindingReq.username);
            int result = weChatService.bindUserWithOpenId(user, bindingReq.openId);
            if(result == BaseResp.SUCCESS){
                return new BaseResp(BaseResp.SUCCESS);
            } else {
                return new BaseResp(BaseResp.USER_WECHAT_BINDING_ERROR);
            }
        } else {
            return new BaseResp(BaseResp.PASSWORD_NOT_MATCH);
        }

    }

}
