package com.mr.media.util;

import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * 令牌
 */
public class TokenHelper {

    /**
     * 为用户生成新的令牌
     */
    public static String newToken(String realName) {
        // 将用户名和当前时间拼接起来，取md5值，作为新的token
        String now = DateHelper.getTimestamp(new Date());
        String seed = realName + now;
        return DigestUtils.md5DigestAsHex(seed.getBytes());
    }

    /**
     * 新令牌过期时间
     */
    public static Date newTokenValidTime() {
        // 过期时间为一个月后
        return DateHelper.addDay(new Date(), 30);
    }
}
