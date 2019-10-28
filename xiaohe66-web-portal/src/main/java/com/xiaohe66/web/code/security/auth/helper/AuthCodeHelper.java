package com.xiaohe66.web.code.security.auth.helper;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.code.security.auth.entity.AuthCode;
import com.xiaohe66.web.code.security.auth.entity.EmailAuthCode;
import com.xiaohe66.web.code.security.auth.entity.ImgAuthCode;
import com.xiaohe66.web.code.security.auth.factory.AuthCodeFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaohe
 * @time 17-11-12 012
 */
@Slf4j
public class AuthCodeHelper {

    private AuthCodeHelper(){}

    /**
     * 验证码超时毫秒数5分钟
     */
    private static final long AUTH_CODE_TIME_OUT_MS = 300000;

    public static ImgAuthCode createImgAuthCode() {
        ImgAuthCode imgAuthCode = AuthCodeFactory.createImgAuthCode();
        WebUtils.setSessionAttr(Final.Str.SESSION_IMG_AUTH_CODE_KEY, imgAuthCode);
        return imgAuthCode;
    }

    public static EmailAuthCode createEmailAuthCode(String email) {
        EmailAuthCode emailAuthCode = AuthCodeFactory.createEmailAuthCode(email);
        WebUtils.setSessionAttr(Final.Str.SESSION_EMAIL_AUTH_CODE_KEY, emailAuthCode);
        return emailAuthCode;
    }

    public static boolean verifyImgCode(String code) {
        AuthCode authCodeObj = WebUtils.getSessionAttr(Final.Str.SESSION_IMG_AUTH_CODE_KEY);
        return verify(code, authCodeObj);
    }

    public static boolean verifyImgCodeNotClearSession(String code) {
        AuthCode authCodeObj = WebUtils.getSessionAttr(Final.Str.SESSION_IMG_AUTH_CODE_KEY);
        return verifyNotClearSession(code, authCodeObj);
    }

    public static boolean verifyEmailCode(String code) {
        AuthCode authCodeObj = WebUtils.getSessionAttr(Final.Str.SESSION_EMAIL_AUTH_CODE_KEY);
        return verify(code, authCodeObj);

    }

    public static boolean verifyEmailCodeNotClearSession(String code) {
        AuthCode authCodeObj = WebUtils.getSessionAttr(Final.Str.SESSION_EMAIL_AUTH_CODE_KEY);
        return verifyNotClearSession(code, authCodeObj);
    }

    private static boolean verify(String code, AuthCode authCodeObj) {
        if (verifyNotClearSession(code, authCodeObj)) {
            WebUtils.removeSessionAttr(Final.Str.SESSION_IMG_AUTH_CODE_KEY);
            return true;
        }
        return false;
    }

    private static boolean verifyNotClearSession(String code, AuthCode authCodeObj) {
        Check.notEmpty(code);
        Check.notEmpty(authCodeObj);

        //超时时间
        long currentTimeMillis = System.currentTimeMillis();

        long ineffectiveTimeMillis = authCodeObj.getCreateTime().getTime() + AUTH_CODE_TIME_OUT_MS;

        if (currentTimeMillis >= ineffectiveTimeMillis) {
            log.debug("令牌已失效，当前时间 : {}, 失效时间 : {}", currentTimeMillis, ineffectiveTimeMillis);
            throw new XhWebException(CodeEnum.B2_TOKEN_TIME_OUT);
        }

        return code.equalsIgnoreCase(authCodeObj.getCode());
    }

}
