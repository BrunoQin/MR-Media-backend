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

    // 管理员自行注册uid重复吗
    public static final int AGENT_REGISTER_EXIST_UID = 18;

    // 查看通知列表状态错误
    public static final int GET_NOTIFICATION_WRONG_STATUS = 19;

    // 更改通知状态状态码相同
    public static final int MARK_NOTIFICATION_SAME_STATUS = 20;

    // 操作权限不足
    public static final int PERMISSION_DENIED = 21;

    // 查看通知列表状态错误
    public static final int GET_REVIEWS_WRONG_STATUS = 22;

    // 标记审核未找到
    public static final int MARK_REVIEW_NO_RESULT = 23;

    // 标记审核类型错误
    public static final int MARK_REVIEW_WRONG_TYPE = 24;

    // 查找用户不存在
    public static final int USER_IS_NOT_EXIST = 25;

    // 查找下级用户uid为空
    public static final int LOOK_UP_SUB_EMPLOYUEES_NULL_UID = 26;

    // 未知错误
    public static final int RESOURCES_NOT_EXIST = 27;

    // 该用户不是艺人
    public static final int USER_NOT_ACTOR = 28;

    // 未知错误
    public static final int UNKNOWN = 999;

    public int errCode;

    public BaseResp(int errCode){
        this.errCode = errCode;
    }

}
