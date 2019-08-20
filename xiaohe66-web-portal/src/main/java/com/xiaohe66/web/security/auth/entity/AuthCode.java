package com.xiaohe66.web.security.auth.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 验证码类
 *
 * @author xh
 * @date 18-03-11 011
 */

public class AuthCode implements Serializable{

    private String code;
    private Date createTime;

    AuthCode(String code, Date createTime) {
        this.code = code;
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
