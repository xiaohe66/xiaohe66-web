package com.xiaohe66.web.code.security.auth.entity;

import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * @author xh
 * @date 18-10-16 016
 */
public class ImgAuthCode extends AuthCode{

    private transient BufferedImage img;

    public ImgAuthCode(BufferedImage img, String code, Date createTime) {
        super(code, createTime);
        this.img = img;
    }

    public BufferedImage getImg() {
        return img;
    }
}
