package com.xiaohe66.web.sys.service;

import com.xiaohe66.web.common.data.ParamFinal;
import com.xiaohe66.web.sys.helper.SysCfgHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件
 *
 * @author xh
 * @version 1.0
 * @time 2018-08-15 11:57
 */
@Service
public class EmailService {

    private Session session;

    private volatile Transport transport;

    public void init() throws MessagingException {
        String smtpHost = SysCfgHelper.getValue(ParamFinal.SYS_EMAIL_SMTP_HOST_KEY);

        Properties props = new Properties();
        props.setProperty("mail.smtp.host",smtpHost);
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.auth", "true");
        session = Session.getInstance(props);

        transport = session.getTransport();
    }


    public void sendEmail(MimeMessage message) throws MessagingException {
        if (!transport.isConnected()) {
            synchronized (EmailService.class){
                if(!transport.isConnected()){
                    String emailHost = SysCfgHelper.getValue(ParamFinal.SYS_EMAIL_HOST_KEY);
                    String emailPwd = SysCfgHelper.getValue(ParamFinal.SYS_EMAIL_PWD_KEY);
                    transport.connect(emailHost, emailPwd);
                }
            }
        }
        transport.sendMessage(message, message.getAllRecipients());
    }

    public MimeMessage createMimeMessage(String targetEmail,String targetName,String subject,String content) throws MessagingException, UnsupportedEncodingException {

        String sendMail = SysCfgHelper.getValue(ParamFinal.SYS_EMAIL_HOST_KEY);
        String usrName = SysCfgHelper.getValue(ParamFinal.SYS_EMAIL_USR_NAME_KEY);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, usrName, ParamFinal.UTF_8));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(targetEmail, targetName,ParamFinal.UTF_8));
        message.setSubject(subject,ParamFinal.UTF_8);

        message.setContent(content,ParamFinal.SYS_EMAIL_CONTENT_TYPE);
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

}
