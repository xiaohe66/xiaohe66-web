package com.xiaohe66.web.sys.dto;

import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.util.StrUtils;

/**
 * @author xiaohe
 * @time 17-10-31 031
 */
public class Result {
    private int code;
    private String msg;
    private Object data;

    private Result(int code,Object data,String msg){
        this.code = code;
        this.data = data;
        this.msg = StrUtils.nullToStr(msg);
    }

    public static Result ok(){
        return new Result(CodeEnum.OK.code(),null,null);
    }

    public static Result ok(Object data){
        return new Result(CodeEnum.OK.code(),data,null);
    }
    public static Result ok(Object data,String msg){
        return new Result(CodeEnum.OK.code(),data,msg);
    }

    public static Result err(CodeEnum codeEnum){
        return new Result(codeEnum.code(),codeEnum.desc(),null);
    }
    public static Result err(CodeEnum codeEnum,String data){
        return new Result(codeEnum.code(),data,codeEnum.desc());
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }
}
