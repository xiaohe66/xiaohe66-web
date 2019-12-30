package com.xiaohe66.web.code.security.service;

import com.xiaohe66.web.base.exception.sec.IllegalOperationException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.IoUtils;
import com.xiaohe66.web.code.sys.ex.EmailSendException;
import com.xiaohe66.web.code.sys.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
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

        Check.notEmpty(authCode, "authCode");
        Check.notEmpty(targetEmail, "targetEmail");
        Check.notEmpty(targetName, "targetName");

        String content = fetchAuthEmailContent(targetName, authCode, handel);
        sendEmail(targetEmail, targetName, content);
    }

    public void sendLink(String link, String targetEmail, String targetName, String handel) {

        Check.notEmpty(link, "link");
        Check.notEmpty(targetEmail, "targetEmail");
        Check.notEmpty(targetName, "targetName");

        String content = fetchAuthLinkContent(targetName, link, handel);

        log.debug("发送注册邮件，目标邮箱 : {}, 注册链接 : {}", targetEmail, link);
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
        String codeTemplateStr;
        try {
            codeTemplateStr = IoUtils.readStringInJar("com/xiaohe66/web/emailTemplate/codeTemplate.html");
        } catch (IOException e) {
            throw new IllegalOperationException("读取验证码模板时发生异常", e);
        }
        return codeTemplateStr.replace("${usrName}", usrName).replace("${handle}", handle).replace("${code}", authCode);
    }

    private String fetchAuthLinkContent(String usrName, String link, String handle) {
        String linkTemplateStr;
        try {
            linkTemplateStr = IoUtils.readStringInJar("com/xiaohe66/web/emailTemplate/linkTemplate.html");
        } catch (IOException e) {
            throw new IllegalOperationException("读取链接模板时发生异常", e);
        }
        return linkTemplateStr.replace("${usrName}", usrName).replace("${handle}", handle).replace("${link}", link);
    }

}
