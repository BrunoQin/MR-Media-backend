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

    // 创建用户名为空
    public static final int CREATED_REALNAME_NULL = 10;

    // 创建用户名已存在
    public static final int CREATED_REALNAME_EXIST = 11;

    // 创建用户权限空
    public static final int CREATED_AUTHORITY_NULL = 12;

    // 创建用户权限无效
    public static final int CREATED_AUTHORITY_INVALID = 13;

    // 上传文件类型不支持
    public static final int UPLOAD_FILE_TYPE_NOT_ALLOW = 14;

    // 获取用户openid失败
    public static final int GET_USER_OPENID_ERROR = 15;

    // 用户微信未绑定
    public static final int USER_WECHAT_NOT_BINDING = 16;

    // 用户微信绑定失败
    public static final int USER_WECHAT_BINDING_ERROR = 17;

    // 未知错误
    public static final int UNKNOWN = 999;

    public int errCode;

    public BaseResp(int errCode){
        this.errCode = errCode;
    }

}
