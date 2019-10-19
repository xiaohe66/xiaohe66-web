package com.xiaohe66.web.code.sys.vo;

import org.springframework.core.io.InputStreamSource;

/**
 * @author xiaohe
 * @time 2019.07.26 11:18
 */
public class EmailAttachment {

    private String fileName;

    private InputStreamSource inputStreamSource;

    public EmailAttachment(String fileName, InputStreamSource inputStreamSource) {
        this.fileName = fileName;
        this.inputStreamSource = inputStreamSource;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStreamSource getInputStreamSource() {
        return inputStreamSource;
    }

    public void setInputStreamSource(InputStreamSource inputStreamSource) {
        this.inputStreamSource = inputStreamSource;
    }
}
