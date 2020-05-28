package com.xiaohe66.web.code.sys.helper;

import com.xiaohe66.common.email.entity.Email;
import com.xiaohe66.common.email.entity.EmailSender;
import com.xiaohe66.common.email.service.EmailSendService;
import com.xiaohe66.common.util.XhEncryptUtils;
import com.xiaohe66.web.base.data.Final;
import lombok.extern.slf4j.Slf4j;

import javax.mail.MessagingException;

/**
 * @author xiaohe
 * @time 2020.05.28 17:33
 */
@Slf4j
public class EmailHelper {

    private static EmailSendService emailSendService;

    private EmailHelper() {
    }

    static {

        String emailAccount = SysCfgHelper.getString(Final.ConfigKey.EMAIL_HOST);
        String emailUserName = SysCfgHelper.getString(Final.ConfigKey.EMAIL_USER_NAME);
        String emailPwdCiphertext = SysCfgHelper.getString(Final.ConfigKey.EMAIL_PWD);
        String smtpHost = SysCfgHelper.getString(Final.ConfigKey.EMAIL_SMTP_HOST);
        String emailPwd = XhEncryptUtils.decode(emailPwdCiphertext);

        EmailSender sender = new EmailSender(emailAccount, emailUserName, emailPwd, smtpHost);
        emailSendService = EmailSendService.getInstance(sender);
    }

    public static boolean sendEmail(Email email) {
        try {
            emailSendService.sendEmail(email);
            return true;

        } catch (MessagingException e) {
            log.error("发送邮件失败", e);
        }
        return false;
    }

}
