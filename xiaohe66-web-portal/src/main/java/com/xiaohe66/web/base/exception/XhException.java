package com.xiaohe66.web.base.exception;

import com.xiaohe66.web.base.data.CodeEnum;

/**
 * 自定义异常类
 *
 * 配合CodeEnum和GlobalExceptionHandler，
 * 实现在代码中任意位置抛出异常时，都会自动统一处理
 *
 * @author xiaohe
 * @time 17-11-05 005
 * @see CodeEnum
 */
public class XhException extends RuntimeException{
    private CodeEnum code;


    public XhException(CodeEnum code){
        this.code = code;
    }

    public XhException(CodeEnum code, String message) {
        super(message);
        this.code = code;
    }

    public XhException(CodeEnum code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public CodeEnum getCode(){
        return code;
    }

    @Override
    public String toString() {
        return "{" + "\"code\":\"" + code.code() + "\""
                + ",\"desc\":\"" + code.desc() + "\""
                + "}";
    }
}
