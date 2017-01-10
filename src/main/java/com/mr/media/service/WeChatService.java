package com.mr.media.service;

import com.mr.media.Application;
import com.mr.media.util.WeChatHelper;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by i321273 on 1/9/17.
 */

@Service
@Component
@EnableScheduling
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

}
