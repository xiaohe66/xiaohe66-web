package com.xiaohe66.web.code.sys.vo;

import javax.mail.internet.InternetAddress;
import java.util.List;

/**
 * @author xiaohe
 * @time 2019.07.26 11:27
 */
public class Email {

    /**
     * 收件人
     */
    private InternetAddress[] targetAddressArr;

    private String subject;
    private String content;

    /**
     * 抄送地址
     */
    private InternetAddress[] ccs;

    /**
     * 附件集合
     */
    private List<EmailAttachment> attachmentList;

    public Email(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public Email(InternetAddress[] targetAddressArr, String subject, String content) {
        this.targetAddressArr = targetAddressArr;
        this.subject = subject;
        this.content = content;
    }

    public InternetAddress[] getTargetAddressArr() {
        return targetAddressArr;
    }

    public void setTargetAddressArr(InternetAddress[] targetAddressArr) {
        this.targetAddressArr = targetAddressArr;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public InternetAddress[] getCcs() {
        return ccs;
    }

    public void setCcs(InternetAddress[] ccs) {
        this.ccs = ccs;
    }

    public List<EmailAttachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<EmailAttachment> attachmentList) {
        this.attachmentList = attachmentList;
    }
}
