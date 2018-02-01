package com.xiaohe66.web.sys.controller;

import com.xiaohe66.web.common.annotation.Page;
import com.xiaohe66.web.common.annotation.Post;
import com.xiaohe66.web.common.annotation.XhController;
import com.xiaohe66.web.common.data.AuthCode;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.StrEnum;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.StrUtils;
import com.xiaohe66.web.common.util.WebUtils;
import com.xiaohe66.web.sys.dto.Result;
import org.apache.shiro.session.Session;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author xiaohe
 * @time 17-11-12 012
 */
@XhController("/validate")
public class ValidateCodeController {

    @Page
    public void img(HttpServletResponse response) throws IOException {
        AuthCode validateCode = new AuthCode();
        Session session = WebUtils.getSession();
        //session.setAttribute(StrEnum.VALIDATE_CODE_STR_KEY.data(),validateCode.getImageStr());

        //将图片输出给浏览器
        BufferedImage image = validateCode.getImage();

        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();

        ImageIO.write(image, "png", os);
    }

    @Post
    public Result validate(String code){
        if(StrUtils.isOneEmpty(code)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"code is empty");
        }
        Session session = WebUtils.getSession();
        String sessionCode = (String) session.getAttribute(StrEnum.VALIDATE_CODE_STR_KEY.data());
        if(code.equalsIgnoreCase(sessionCode)){
            return Result.ok(true);
        }else{
            return Result.err(CodeEnum.PARAM_ERR,"code is wrong");
        }
    }
}
