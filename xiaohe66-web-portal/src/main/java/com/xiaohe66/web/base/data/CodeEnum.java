package com.xiaohe66.web.base.data;

/**
 * 异常码类，一般情况下是和 {@link com.xiaohe66.web.base.exception.XhWebException} 类一起使用的
 * <p>
 * msg字段会被发送至客户端
 *
 * @author xiaohe
 * @time 17-11-02 002
 */
public enum CodeEnum {

    // 默认成功返回值
    OK(100, "成功")

    // 默认失败返回值
    , EXCEPTION(-1, "系统繁忙")

    // 4开头的，属于客户端的问题
    , B0_ILLEGAL_REQUEST(400, "非法请求")

    // 41开头的，业务参数问题
    , B1_ILLEGAL_PARAM(410, "非法参数")
    , B1_MISSING_PARAM(411, "缺少参数")
    , B1_OVER_MAX_VALUE(412, "超过最大值")
    , B1_OVER_MIN_VALUE(413, "超过最小值")
    , B1_OBJ_ALREADY_EXIST(414, "对象已存在")
    , B1_OBJ_NOT_EXIST(415, "对象不存在")

    // 42开头的，认证或权限问题
    , B2_ILLEGAL_OPERATE(420, "非法操作")
    , B2_NOT_LOGGED_IN(421, "用户未登录")
    , B2_USER_NOT_EXIST(422, "用户不存在")
    , B2_PASSWORD_ERROR(423, "密码错误")
    , B2_TOKEN_TIME_OUT(424, "令牌已失效")
    , B2_TOKEN_ERROR(425, "令牌错误")
    , B2_MISSING_PERMITTED(426, "无操作权限")

    // 5开头的，属于服务端的问题
    , SYSTEM_ERROR(500, "系统异常")
    , IO_EXCEPTION(501, "IO异常")
    , RUNTIME_EXCEPTION(502, "运行时异常")

    ;

    CodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int code() {
        return code;
    }

    public String msg() {
        return msg;
    }

    @Override
    public String toString() {
        return "{" + code + ", " + msg + "}";
    }
}
