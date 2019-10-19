package com.xiaohe66.web.code.sys.service;

import com.sun.net.ssl.internal.ssl.Provider;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.util.EncoderUtils;
import com.xiaohe66.web.code.sys.ex.EmailSendException;
import com.xiaohe66.web.code.sys.helper.SysCfgHelper;
import com.xiaohe66.web.code.sys.vo.Email;
import com.xiaohe66.web.code.sys.vo.EmailAttachment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * 邮件
 *
 * @author xh
 * @version 1.0
 * @time 2018-08-15 11:57
 */
@Service
@Slf4j
@DependsOn("sysCfgService")
public class EmailService implements InitializingBean {

    private Session session;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (session != null) {
            log.warn("请不要自己调用 afterPropertiesSet()方法");
            return;
        }
        log.info("初始化邮件发送服务session");

        String smtpHost = SysCfgHelper.getString(Final.Str.SYS_EMAIL_SMTP_HOST_KEY);

        log.debug("smtpHost : {}", smtpHost);

        Security.addProvider(new Provider());
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", smtpHost);
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");

        session = Session.getInstance(props);

        log.info("邮件发送服务session初始化完成");
    }

    public void sendEmail(String targetEmail, String targetName, String subject, String content) throws MessagingException, EmailSendException {
        InternetAddress[] internetAddress;
        try {
            internetAddress = new InternetAddress[]{
                    new InternetAddress(targetEmail, targetName, Final.Str.UTF_8)
            };
        } catch (UnsupportedEncodingException e) {
            throw new EmailSendException(e);
        }
        Email email = new Email(internetAddress, subject, content);
        sendEmail(email);
    }

    public void sendEmail(Email email) throws MessagingException {
        log.info("发送邮件,主题 : {}", email.getSubject());
        check(email);
        try {
            MimeMessage message = createMimeMessage(email);
            String emailAccount = SysCfgHelper.getString(Final.Str.SYS_EMAIL_HOST_KEY);
            String emailPwd = EncoderUtils.base64Decode(SysCfgHelper.getString(Final.Str.SYS_EMAIL_PWD_KEY));
            Transport.send(message, emailAccount, emailPwd);
            log.info("邮件发送成功,主题 : {}", email.getSubject());

        } catch (SendFailedException e) {
            log.warn("邮件主题地址无效, message : {}", e.getMessage());
            handleSendFailedException(e, email);

        } catch (MessagingException e) {
            log.error("网络连接失败, message : {}, 主题 : {}", e.getMessage(), email.getSubject());
            throw e;

        } catch (Exception e) {
            log.error("邮件发送失败, message : {}, 主题 : {}", e.getMessage(), email.getSubject());
            throw new EmailSendException(e);
        }
    }


    private void handleSendFailedException(SendFailedException e, Email email) {

        log.warn("存在错误的邮箱地址");

        InternetAddress[] invalidAddresses = castToInternetAddress(e.getInvalidAddresses());
        InternetAddress[] validSentAddresses = castToInternetAddress(e.getValidSentAddresses());
        InternetAddress[] validUnsentAddresses = castToInternetAddress(e.getValidUnsentAddresses());

        if (log.isWarnEnabled()) {

            log.warn("错误的邮箱地址 : {}", Arrays.toString(
                    Stream.of(invalidAddresses).map(InternetAddress::getAddress).toArray()));

            log.warn("正确已发送的邮箱地址 : {}", Arrays.toString(
                    Stream.of(validSentAddresses).map(InternetAddress::getAddress).toArray()));

            log.warn("正确未发送的邮箱地址 : {}", Arrays.toString(
                    Stream.of(validUnsentAddresses).map(InternetAddress::getAddress).toArray()));
        }

        if (validUnsentAddresses.length == 0) {
            log.warn("没有正确未发送的邮箱地址，因而忽略");
            return;
        }

        InternetAddress[] targetAddressArr = email.getTargetAddressArr();
        List<InternetAddress> targetAddressList = new ArrayList<>(targetAddressArr.length);
        Collections.addAll(targetAddressList, targetAddressArr);

        InternetAddress[] ccs = email.getCcs();
        List<InternetAddress> cssList = new ArrayList<>(ccs.length);
        Collections.addAll(cssList, ccs);

        // 异常信息中，错误的邮箱地址是包含了收件人和抄送人的，
        // 但无法判断是属于哪一个，因此需要从收件人和抄送人中都检查并删除错误的邮箱地址
        for (InternetAddress invalidAddress : invalidAddresses) {
            targetAddressList.remove(invalidAddress);
            cssList.remove(invalidAddress);
        }

        email.setTargetAddressArr(targetAddressList.toArray(new InternetAddress[0]));
        email.setCcs(cssList.toArray(new InternetAddress[0]));

        try {
            MimeMessage message = createMimeMessage(email);

            log.warn("尝试重新发送");
            String emailAccount = SysCfgHelper.getString(Final.Str.SYS_EMAIL_HOST_KEY);
            String emailPwd = EncoderUtils.base64Decode(SysCfgHelper.getString(Final.Str.SYS_EMAIL_PWD_KEY));
            Transport.send(message, emailAccount, emailPwd);
            log.warn("邮件发送成功");

        } catch (Exception e1) {

            log.error("邮件发送异常，经过处理后仍无法正常发送, message : {}", e1.getMessage());
            throw new EmailSendException(e1);
        }
    }

    private InternetAddress[] castToInternetAddress(Address[] addresses) {
        if (addresses == null) {
            return new InternetAddress[0];
        }
        InternetAddress[] result = new InternetAddress[addresses.length];
        for (int i = 0; i < addresses.length; i++) {
            result[i] = (InternetAddress) addresses[i];
        }
        return result;
    }

    private void check(Email email) {
        Objects.requireNonNull(email);
        if (email.getTargetAddressArr() == null || email.getTargetAddressArr().length == 0) {
            throw new NullPointerException("targetAddressArr cannot be empty");
        }
        Objects.requireNonNull(email.getSubject(), "subject cannot be null");
        Objects.requireNonNull(email.getContent(), "content cannot be null");
    }

    private MimeMessage createMimeMessage(Email email) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = new MimeMessage(session);

        String emailUserName = SysCfgHelper.getString(Final.Str.SYS_EMAIL_USR_NAME_KEY);
        String emailAccount = SysCfgHelper.getString(Final.Str.SYS_EMAIL_HOST_KEY);

        MimeMessageHelper helper = new MimeMessageHelper(message, true, Final.Str.UTF_8);
        helper.setFrom(new InternetAddress(emailAccount, emailUserName, Final.Str.UTF_8));
        helper.setTo(email.getTargetAddressArr());
        helper.setSubject(email.getSubject());
        helper.setText(email.getContent(), true);

        // 抄送
        if (email.getCcs() != null) {
            helper.setCc(email.getCcs());
        }

        // 附件
        List<EmailAttachment> attachmentList = email.getAttachmentList();
        if (attachmentList != null) {
            for (EmailAttachment emailAttachment : attachmentList) {
                helper.addAttachment(emailAttachment.getFileName(), emailAttachment.getInputStreamSource());
            }
        }

        message.setSentDate(new Date());
        return message;
    }

}
