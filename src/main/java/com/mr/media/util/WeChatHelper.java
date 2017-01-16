package com.mr.media.util;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by i321273 on 1/9/17.
 */

@Component
public class WeChatHelper {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${weChat.token}")
    String token;
    @Value("${weChat.appid}")
    String appId;
    @Value("${weChat.appsecret}")
    String appSecret;
    @Value("${weChat.access_token.public.url}")
    String publicUrl;
    @Value("${weChat.access_token.private.url}")
    String privateUrl;

    private static final Object publicAccessTokenLock = new Object();
    private static String publicAccessToken = null;
    private static Date publicAccessTokenValidTime = null;

    public Boolean checkSignature(String signature, String timestamp, String nonce){

        String[] arr = new String[]{token,timestamp,nonce};

        Arrays.sort(arr);

        StringBuffer content = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }

        String temp = getSha1(content.toString());

        return temp.equals(signature);

    }

    public String getSha1(String str){
        if (null == str || 0 == str.length()){
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 用户认证所需access token,通过该数据获取openid
    public String getOpenIdByCode(String code){
        List<NameValuePair> params = new LinkedList<>();
        params.add(new BasicNameValuePair("appid", appId));
        params.add(new BasicNameValuePair("secret", appSecret));
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));

        String resp = sendGET(privateUrl, params);

        try {
            JSONObject jsonObj = new JSONObject(resp);
            if (jsonObj.has("errcode")) {
                logger.error("查询用户openid失败: " + jsonObj.getString("errmsg"));
                return null;
            } else {
                String openId = jsonObj.getString("openid");
                logger.info("查询用户openid成功: " + openId);
                return openId;
            }
        } catch (Exception e) {
            logger.error("查询用户openid失败: ", e);
            return null;
        }
    }

    // 对于消息或者广泛接口(暂未用到)******
    public String getPublicAccessToken(){
        if(publicAccessToken == null ||
                publicAccessTokenValidTime == null ||
                new Date().after(publicAccessTokenValidTime)) {
            synchronized (publicAccessTokenLock) {
                if(publicAccessToken == null ||
                        publicAccessTokenValidTime == null ||
                        new Date().after(publicAccessTokenValidTime)) {
                    updatePublicAccessToken();
                }
            }
        }

        return publicAccessToken;
    }

    private void updatePublicAccessToken() {

        List<NameValuePair> params = new LinkedList<>();
        params.add(new BasicNameValuePair("appid", appId));
        params.add(new BasicNameValuePair("secret", appSecret));
        params.add(new BasicNameValuePair("grant_type", "client_credential"));

        String resp = sendGET(publicUrl, params);
        try {
            JSONObject jsonObj = new JSONObject(resp);
            if (jsonObj.has("errcode")) {
                logger.error("查询公众号token失败: " + jsonObj.getString("errmsg"));
                publicAccessToken = null;
            } else {
                publicAccessToken = jsonObj.getString("access_token");
                logger.info("查询公众号token成功: " + publicAccessToken);
                publicAccessTokenValidTime = DateHelper.addSecond(new Date(),
                        jsonObj.getInt("expires_in") - 600);
            }
        } catch (Exception e) {
            logger.error("查询公众号token失败", e);
            publicAccessToken = null;
        }
    }

    private String sendGET(String url, List<NameValuePair> params) {
        String paramString = URLEncodedUtils.format(params, "UTF-8");
        HttpGet httpGet = new HttpGet(url + "?" + paramString);
        // 发送请求
        try (CloseableHttpClient httpclient = HttpClients.createDefault();

             CloseableHttpResponse resp = httpclient.execute(httpGet)) {

            int code = resp.getStatusLine().getStatusCode();
            if (code != HttpStatus.SC_OK) {
                logger.warn("访问url出错 " + url + "?" + paramString);
                logger.warn("http状态码 " + code);
                return "";
            }

            return EntityUtils.toString(resp.getEntity(), "UTF-8");

        } catch (Exception e) {
            logger.warn("访问url出错 " + url + "?" + paramString);
            return "";
        }
    }

}
