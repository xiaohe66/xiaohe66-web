package com.xiaohe66.web.code.security.auth.entity;

import java.util.Date;

/**
 * @author xh
 * @date 18-10-16 016
 */
public class EmailAuthCode extends AuthCode{

    private String email;

    public EmailAuthCode(String email, String code, Date createTime) {
        super(code, createTime);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
