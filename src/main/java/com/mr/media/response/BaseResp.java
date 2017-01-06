package com.mr.media.response;

public class BaseResp {
    // 全局错误码

    // 成功
    public static final int SUCCESS = 0;
    // 用户名密码不匹配
    public static final int PASSWORD_NOT_MATCH = 1;
    // 用户未激活
    public static final int USER_DEACTIVE = 2;
    // 用户token无效
    public static final int WRONT_TOKEN = 3;
    // 密码输入错误
    public static final int WRONG_OLD_PASSWORD = 5;
    // 确认密码不正确
    public static final int CONFIRM_NOT_MATCH = 6;
    // 新旧密码相同
    public static final int NEW_AND_OLD_SAME = 7;
    // 页码错误
    public static final int WRONG_PAGE_PARAM = 8;
    // 权限不足
    public static final int PERMITION_NOT_ALLOW = 9;

    // 未知错误
    public static final int UNKNOWN = 999;

    public int errCode;

    public BaseResp(int errCode){
        this.errCode = errCode;
    }

}
