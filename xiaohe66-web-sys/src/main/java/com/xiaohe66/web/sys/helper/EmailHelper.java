package com.xiaohe66.web.sys.helper;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.IoUtils;
import com.xiaohe66.web.sys.service.EmailService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

/**
 * @author xh
 * @date 18-10-16 016
 */
public class EmailHelper {

    private static EmailService emailService;

    private static final String DEFAULT_EMAIL_SUBJECT = "xiaohe66.com";

    private static String codeTemplateStr;
    private static String linkTemplateStr;

    public static void init(EmailService emailService) throws MessagingException {
        EmailHelper.emailService = emailService;
        emailService.init();

        codeTemplateStr = IoUtils.readStringInJar("com/xiaohe66/web/emailTemplate/codeTemplate.html");
        linkTemplateStr = IoUtils.readStringInJar("com/xiaohe66/web/emailTemplate/linkTemplate.html");
    }

    public static void sendAuthCode(String authCode,String targetEmail,String targetName,String handel){
        Check.notNullCheck(authCode,targetEmail,targetName,handel);

        String content = fetchAuthEmailContent(targetName,authCode,handel);

        try {
            emailService.createMimeMessage(targetEmail,targetName,DEFAULT_EMAIL_SUBJECT,content);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new XhException(CodeEnum.RUNTIME_EXCEPTION,"发送邮件失败");
        }
    }

    public static void sendLink(String link,String targetEmail,String targetName,String handel){
        Check.notNullCheck(link,targetEmail,targetName,handel);

        String content = fetchAuthLinkContent(targetName,link,handel);

        try {
            emailService.sendEmail(emailService.createMimeMessage(targetEmail,targetName,DEFAULT_EMAIL_SUBJECT,content));
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new XhException(CodeEnum.RUNTIME_EXCEPTION,"发送邮件失败");
        }
    }

    public static String fetchAuthEmailContent(String usrName,String authCode,String handle){
        return codeTemplateStr.replace("${usrName}",usrName).replace("${handle}",handle).replace("${code}",authCode);
    }

    public static String fetchAuthLinkContent(String usrName,String link,String handle){
        return linkTemplateStr.replace("${usrName}",usrName).replace("${handle}",handle).replace("${link}",link);
    }
}
