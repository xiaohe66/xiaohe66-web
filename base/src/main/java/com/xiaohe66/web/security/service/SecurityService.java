package com.xiaohe66.web.security.service;

import com.xiaohe66.web.common.auth.AuthCode;
import com.xiaohe66.web.common.auth.AuthCodeHelper;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.ParamFinal;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.WebUtils;
import com.xiaohe66.web.org.dto.UsrDto;
import com.xiaohe66.web.sys.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * @author xh
 * @version 1.0
 * @time 2018-08-16 15:47
 */
@Service
public class SecurityService {

    private static final String AUTH_CODE_EMAIL_SUBJECT = "xiaohe66验证码";
    private static final String AUTH_CODE_EMAIL_CONTENT = "【xiaohe66】验证码:{code}，本验证码有效时间5分钟，请勿告知他人。";

    @Autowired
    private EmailService emailService;

    public void sendAuthCode(){
        UsrDto usrDto = getCurrentUsr();
        sendAuthCode(usrDto.getEmail(),usrDto.getUsrName());
    }

    public void sendAuthCode(String targetEmail,String targetName){

        AuthCode authCode = AuthCodeHelper.createAuthCode();

        WebUtils.setSessionAttr(ParamFinal.SESSION_AUTH_CODE_KEY,authCode);

        String emailContent = AUTH_CODE_EMAIL_CONTENT.replace("{code}",authCode.getCode());

        try {
            MimeMessage message = emailService.createMimeMessage(targetEmail,targetName, AUTH_CODE_EMAIL_SUBJECT,emailContent);
            emailService.sendEmail(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new XhException(CodeEnum.RUNTIME_EXCEPTION,e);
        }
    }

    public static UsrDto getCurrentUsr(){
        UsrDto usrDto = WebUtils.getSessionAttr(ParamFinal.SESSION_UER_KEY);
        if(usrDto == null){
            throw new XhException(CodeEnum.NOT_LOGGED_IN);
        }
        return usrDto;
    }

}
