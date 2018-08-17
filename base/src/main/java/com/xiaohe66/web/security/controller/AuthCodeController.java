package com.xiaohe66.web.security.controller;

import com.xiaohe66.web.common.annotation.Get;
import com.xiaohe66.web.common.annotation.Page;
import com.xiaohe66.web.common.annotation.XhController;
import com.xiaohe66.web.common.auth.AuthCode;
import com.xiaohe66.web.common.auth.AuthCodeHelper;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.ParamFinal;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.StrUtils;
import org.springframework.web.bind.annotation.PathVariable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
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

        AuthCode authCode = AuthCodeHelper.createAuthCode();

        response.setContentType(ParamFinal.CONTENT_TYPE_IMAGE_PNG);
        OutputStream os = response.getOutputStream();

        ImageIO.write(authCode.getImg(), ParamFinal.FILE_TYPE_PNG, os);
    }

    @Get("/{code}")
    public Boolean authCode(@PathVariable("code") String code){
        if(StrUtils.isEmpty(code)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"code is null");
        }

        return AuthCodeHelper.verifyNotClearSession(code);
    }

}
