package com.mr.media.service;

import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.util.WeChatHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by i321273 on 1/9/17.
 */

@Service
public class WeChatService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    WeChatHelper weChatHelper;

    public Boolean weChatCheckSignature(String signature, String timestamp, String nonce){

        if(weChatHelper.checkSignature(signature, timestamp, nonce)){
            return true;
        } else {
            return false;
        }

    }

    public String getOpenIdByCode(String code){
        String openId = weChatHelper.getOpenIdByCode(code);
        return openId;
    }

    public int bindUserWithOpenId(User user, String openId){
        user.setOpenId(openId);
        try {
            user.save();
            return BaseResp.SUCCESS;
        } catch (Exception e) {
            logger.error("绑定用户失败", e);
            return BaseResp.UNKNOWN;
        }
    }

}
