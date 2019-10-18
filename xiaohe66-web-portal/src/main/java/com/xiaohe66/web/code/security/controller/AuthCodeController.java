package com.xiaohe66.web.code.security.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.StrUtils;
import com.xiaohe66.web.code.security.auth.helper.AuthCodeHelper;
import org.springframework.web.bind.annotation.PathVariable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author xh
 * @date 18-03-11 011
 */
@XhController("/authCode")
public class AuthCodeController {

    @Page("/img")
    public void code(HttpServletResponse response) throws IOException {

        BufferedImage authCodeImg = AuthCodeHelper.createImgAuthCode().getImg();

        response.setContentType(Final.Str.CONTENT_TYPE_IMAGE_PNG);
        OutputStream os = response.getOutputStream();

        ImageIO.write(authCodeImg, Final.Str.FILE_TYPE_PNG, os);
    }

    @Get("/{code}")
    public Boolean authCode(@PathVariable("code") String code) {
        if (StrUtils.isEmpty(code)) {
            throw new XhWebException(CodeEnum.NULL_EXCEPTION, "code is null");
        }

        return AuthCodeHelper.verifyImgCodeNotClearSession(code);
    }

}
