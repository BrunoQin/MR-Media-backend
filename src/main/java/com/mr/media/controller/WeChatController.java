package com.mr.media.controller;

import com.mr.media.service.WeChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/check_signature", method = RequestMethod.GET)
    public String weChatCheckSignature(String signature, String timestamp, String nonce, String echostr){

        if(weChatService.weChatCheckSignature(signature, timestamp, nonce)){
            return echostr;
        } else {
            return signature + "_" + timestamp + "_" + nonce;
        }

    }

}
