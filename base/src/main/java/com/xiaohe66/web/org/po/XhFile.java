package com.xiaohe66.web.org.po;

import com.xiaohe66.web.common.base.BasePoDetailed;

/**
 * @author xiaohe
 * @time 17-11-11 011
 */
public class XhFile extends BasePoDetailed {

    private String fileName;
    private String fileUrl;
    private String fileDesc;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"fileName\":\"" + fileName + "\""
                + ",\"createTime\":" + createTime
                + ",\"fileUrl\":\"" + fileUrl + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"fileDesc\":\"" + fileDesc + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
