package com.xiaohe66.web.security.auth.entity;

import java.util.Date;

/**
 * @author xh
 * @date 18-10-16 016
 */
public class EmailAuthCode extends AuthCode{

    private String email;

    public EmailAuthCode(String img, String code, Date createTime) {
        super(code, createTime);
        this.email = img;
    }

    public String getEmail() {
        return email;
    }
}
