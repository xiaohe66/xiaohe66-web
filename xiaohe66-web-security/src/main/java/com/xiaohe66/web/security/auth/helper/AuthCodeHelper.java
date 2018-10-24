package com.xiaohe66.web.security.auth.helper;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.MsgException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.security.auth.entity.AuthCode;
import com.xiaohe66.web.security.auth.entity.EmailAuthCode;
import com.xiaohe66.web.security.auth.entity.ImgAuthCode;
import com.xiaohe66.web.security.auth.factory.AuthCodeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiaohe
 * @time 17-11-12 012
 */
public class AuthCodeHelper {

    private static final Logger LOG = LoggerFactory.getLogger(AuthCodeHelper.class);

    /**
     * 验证码超时毫秒数5分钟
     */
    private static final long AUTH_CODE_TIME_OUT_MS = 300000;

    public static ImgAuthCode createImgAuthCode(){
        ImgAuthCode imgAuthCode = AuthCodeFactory.createImgAuthCode();
        WebUtils.setSessionAttr(Final.Str.SESSION_IMG_AUTH_CODE_KEY,imgAuthCode);
        return imgAuthCode;
    }

    public static EmailAuthCode createEmailAuthCode(String email){
        EmailAuthCode emailAuthCode = AuthCodeFactory.createEmailAuthCode(email);
        WebUtils.setSessionAttr(Final.Str.SESSION_EMAIL_AUTH_CODE_KEY,emailAuthCode);
        return emailAuthCode;
    }

    public static boolean verifyImgCode(String code){
        AuthCode authCodeObj = WebUtils.getSessionAttr(Final.Str.SESSION_IMG_AUTH_CODE_KEY);
        return verify(code,authCodeObj);
    }

    public static boolean verifyImgCodeNotClearSession(String code){
        AuthCode authCodeObj = WebUtils.getSessionAttr(Final.Str.SESSION_IMG_AUTH_CODE_KEY);
        return verifyNotClearSession(code,authCodeObj);
    }

    public static boolean verifyEmailCode(String code){
        AuthCode authCodeObj = WebUtils.getSessionAttr(Final.Str.SESSION_EMAIL_AUTH_CODE_KEY);
        return verify(code,authCodeObj);

    }
    public static boolean verifyEmailCodeNotClearSession(String code){
        AuthCode authCodeObj = WebUtils.getSessionAttr(Final.Str.SESSION_EMAIL_AUTH_CODE_KEY);
        return verifyNotClearSession(code,authCodeObj);
    }

    private static boolean verify(String code,AuthCode authCodeObj) {
        Check.notEmptyCheck(code,authCodeObj);

        //超时时间
        long difference = System.currentTimeMillis()-authCodeObj.getCreateTime().getTime();

        if(difference >= AUTH_CODE_TIME_OUT_MS){
            throw new MsgException(CodeEnum.AUTH_CODE_TIME_OUT);
        }

        if(code.equalsIgnoreCase(authCodeObj.getCode())){
            WebUtils.removeSessionAttr(Final.Str.SESSION_IMG_AUTH_CODE_KEY);
            return true;
        }

        return false;
    }

    private static boolean verifyNotClearSession(String code,AuthCode authCodeObj) {
        Check.notEmptyCheck(code,authCodeObj);

        //超时时间
        long difference = System.currentTimeMillis()-authCodeObj.getCreateTime().getTime();

        if(difference >= AUTH_CODE_TIME_OUT_MS){
            throw new MsgException(CodeEnum.AUTH_CODE_TIME_OUT);
        }

        return code.equalsIgnoreCase(authCodeObj.getCode());
    }

}
