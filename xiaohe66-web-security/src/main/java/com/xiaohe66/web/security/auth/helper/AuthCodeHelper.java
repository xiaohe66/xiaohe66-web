package com.xiaohe66.web.security.auth.helper;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.ParamFinal;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.security.auth.entity.AuthCode;
import com.xiaohe66.web.security.auth.entity.EmailAuthCode;
import com.xiaohe66.web.security.auth.entity.ImgAuthCode;
import com.xiaohe66.web.security.auth.factory.AuthCodeFactory;
import com.xiaohe66.web.sys.helper.EmailHelper;
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

    private static final String REGISTER_VERIFY = "http://xiaohe66.com/org/usr/verify/";


    public static ImgAuthCode createImgAuthCode(){
        ImgAuthCode imgAuthCode = AuthCodeFactory.createImgAuthCode();
        WebUtils.setSessionAttr(ParamFinal.SESSION_IMG_AUTH_CODE_KEY,imgAuthCode);
        return imgAuthCode;
    }

    /**
     * 发送链接类型的内容到指定邮箱
     *
     * xh todo:需要把link内容中简短的验证码改成token形式的长代码
     *
     * @param targetEmail   目标邮箱
     * @param targetName    目标邮箱的姓名
     * @param handel        当前操作类型（注册、重置密码等等）
     * @return  EmailAuthCode
     */
    public static EmailAuthCode sendEmailCode(String targetEmail, String targetName, String handel){

        EmailAuthCode emailAuthCode = AuthCodeFactory.createEmailAuthCode(targetEmail);

        //发送邮件
        EmailHelper.sendAuthCode(emailAuthCode.getCode(),targetEmail,targetName,handel);

        WebUtils.setSessionAttr(ParamFinal.SESSION_EMAIL_AUTH_CODE_KEY,emailAuthCode);
        return emailAuthCode;
    }

    public static boolean verifyImgCode(String code){
        AuthCode authCodeObj = WebUtils.getSessionAttr(ParamFinal.SESSION_IMG_AUTH_CODE_KEY);
        return verify(code,authCodeObj);
    }

    public static boolean verifyImgCodeNotClearSession(String code){
        AuthCode authCodeObj = WebUtils.getSessionAttr(ParamFinal.SESSION_IMG_AUTH_CODE_KEY);
        return verifyNotClearSession(code,authCodeObj);
    }

    public static boolean verifyEmailCode(String code){
        AuthCode authCodeObj = WebUtils.getSessionAttr(ParamFinal.SESSION_EMAIL_AUTH_CODE_KEY);
        return verify(code,authCodeObj);

    }
    public static boolean verifyEmailCodeNotClearSession(String code){
        AuthCode authCodeObj = WebUtils.getSessionAttr(ParamFinal.SESSION_EMAIL_AUTH_CODE_KEY);
        return verifyNotClearSession(code,authCodeObj);
    }

    private static boolean verify(String code,AuthCode authCodeObj) {
        Check.notEmptyCheck(code,authCodeObj);

        //超时时间
        long difference = System.currentTimeMillis()-authCodeObj.getCreateTime().getTime();

        if(difference >= AUTH_CODE_TIME_OUT_MS){
            throw new XhException(CodeEnum.AUTH_CODE_TIME_OUT);
        }

        if(code.equalsIgnoreCase(authCodeObj.getCode())){
            WebUtils.removeSessionAttr(ParamFinal.SESSION_IMG_AUTH_CODE_KEY);
            return true;
        }

        return false;
    }

    private static boolean verifyNotClearSession(String code,AuthCode authCodeObj) {
        Check.notEmptyCheck(code,authCodeObj);

        //超时时间
        long difference = System.currentTimeMillis()-authCodeObj.getCreateTime().getTime();

        if(difference >= AUTH_CODE_TIME_OUT_MS){
            throw new XhException(CodeEnum.AUTH_CODE_TIME_OUT);
        }

        return code.equalsIgnoreCase(authCodeObj.getCode());
    }

}
