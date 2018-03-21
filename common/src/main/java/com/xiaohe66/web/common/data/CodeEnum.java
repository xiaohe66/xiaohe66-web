package com.xiaohe66.web.common.data;

/**
 *
 * 异常码类
 *
 * @author xiaohe
 * @time 17-11-02 002
 */
public enum CodeEnum {
    OK(200,"成功")
    ,EXCEPTION(500,"未知异常")

    ,NULL_EXCEPTION(501,"传入对象为空")
    ,NOT_IMPLEMENTED(502,"抽象方法未实现，请重写后再使用")
    ,DISABLE_FUNCTION(503,"该方法已被禁用")

    //51* 格式转换类 ---
    ,NUMBER_FORMAT_EXCEPTION(510,"无法转换为数字类型")
    ,DATE_FORMAT_EXCEPTION(511,"无法转换为日期类型")

    //40* 资源
    ,RESOURCE_NOT_FOUND(401,"资源没有找到")
    ,PARAM_ERR(402,"参数错误")
    ,OBJ_ALREADY_EXIST(403,"对象已存在")

    ,USR_NOT_EXIST(601,"用户不存在")
    ,PASSWORD_ERROR(602,"密码错误")
    ,NOT_LOGGED_IN(603,"用户未登录")
    ,NOT_PERMISSION(604,"权限不足")

    ,IO_EXCEPTION(701,"IO异常")

    ;

    CodeEnum(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public int code() {
        return code;
    }

    public String desc() {
        return desc;
    }
}
