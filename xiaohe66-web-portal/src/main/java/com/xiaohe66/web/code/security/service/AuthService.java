package com.xiaohe66.web.code.security.service;

import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.IoUtils;
import com.xiaohe66.web.code.sys.ex.EmailSendException;
import com.xiaohe66.web.code.sys.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiaohe
 * @time 2019.10.19 21:35
 */
@Service
@Slf4j
public class AuthService {

    private static final String DEFAULT_EMAIL_SUBJECT = "xiaohe66.com";

    private EmailService emailService;

    private ExecutorService executorService;

    public AuthService(EmailService emailService) {
        this.emailService = emailService;
        executorService = Executors.newFixedThreadPool(2);
    }

    public void sendAuthCode(String authCode, String targetEmail, String targetName, String handel) {
        Check.notNullCheck(authCode, targetEmail, targetName, handel);
        String content = fetchAuthEmailContent(targetName, authCode, handel);
        sendEmail(targetEmail, targetName, content);
    }

    public void sendLink(String link, String targetEmail, String targetName, String handel) {
        Check.notNullCheck(link, targetEmail, targetName, handel);
        String content = fetchAuthLinkContent(targetName, link, handel);
        sendEmail(targetEmail, targetName, content);
    }

    private void sendEmail(String targetEmail, String targetName, String content) {
        executorService.submit(() -> {
            try {
                emailService.sendEmail(targetEmail, targetName, DEFAULT_EMAIL_SUBJECT, content);
            } catch (MessagingException e) {
                log.error("网络连接失败", e);
            } catch (EmailSendException e) {
                log.error("邮件发送失败", e);
            }
        });
    }

    private String fetchAuthEmailContent(String usrName, String authCode, String handle) {
        String codeTemplateStr = IoUtils.readStringInJar("com/xiaohe66/web/emailTemplate/codeTemplate.html");
        return codeTemplateStr.replace("${usrName}", usrName).replace("${handle}", handle).replace("${code}", authCode);
    }

    private String fetchAuthLinkContent(String usrName, String link, String handle) {
        String linkTemplateStr = IoUtils.readStringInJar("com/xiaohe66/web/emailTemplate/linkTemplate.html");
        return linkTemplateStr.replace("${usrName}", usrName).replace("${handle}", handle).replace("${link}", link);
    }

}
