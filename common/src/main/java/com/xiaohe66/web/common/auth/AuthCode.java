package com.xiaohe66.web.common.auth;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Date;

/**
 * 验证码类
 *
 * @author xh
 * @date 18-03-11 011
 */

public class AuthCode implements Serializable{

    private transient BufferedImage img;
    private String code;
    private Date createTime;

    public AuthCode(BufferedImage img, String code, Date createTime) {
        this.img = img;
        this.code = code;
        this.createTime = createTime;
    }

    public BufferedImage getImg() {
        return img;
    }

    public String getCode() {
        return code;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
