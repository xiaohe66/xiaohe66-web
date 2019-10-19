package com.xiaohe66.web.code.sys.helper;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.IoUtils;
import com.xiaohe66.web.code.sys.service.EmailService;
import com.xiaohe66.web.code.sys.vo.Email;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
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
        codeTemplateStr = IoUtils.readStringInJar("com/xiaohe66/web/emailTemplate/codeTemplate.html");
        linkTemplateStr = IoUtils.readStringInJar("com/xiaohe66/web/emailTemplate/linkTemplate.html");
    }

    public static void sendAuthCode(String authCode, String targetEmail, String targetName, String handel) {
        Check.notNullCheck(authCode, targetEmail, targetName, handel);
        String content = fetchAuthEmailContent(targetName, authCode, handel);
        sendEmail(targetEmail, targetName, content);
    }

    public static void sendLink(String link, String targetEmail, String targetName, String handel) {
        Check.notNullCheck(link, targetEmail, targetName, handel);
        String content = fetchAuthLinkContent(targetName, link, handel);
        sendEmail(targetEmail, targetName, content);
    }

    private static void sendEmail(String targetEmail, String targetName, String content) {
        try {
            InternetAddress[] internetAddress = new InternetAddress[]{
                    new InternetAddress(targetEmail, targetName, "UTF-8")
            };
            Email email = new Email(internetAddress, DEFAULT_EMAIL_SUBJECT, content);
            emailService.sendEmail(email);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new XhWebException(CodeEnum.RUNTIME_EXCEPTION, e);
        }
    }

    private static String fetchAuthEmailContent(String usrName, String authCode, String handle) {
        return codeTemplateStr.replace("${usrName}", usrName).replace("${handle}", handle).replace("${code}", authCode);
    }

    private static String fetchAuthLinkContent(String usrName, String link, String handle) {
        return linkTemplateStr.replace("${usrName}", usrName).replace("${handle}", handle).replace("${link}", link);
    }
}
