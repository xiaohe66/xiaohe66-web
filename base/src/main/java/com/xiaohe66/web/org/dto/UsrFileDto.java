package com.xiaohe66.web.org.dto;

import com.xiaohe66.web.common.base.BaseDtoDetailed;

/**
 * @author xh
 * @date 18-04-08 008
 */
public class UsrFileDto extends BaseDtoDetailed{
    private String fileName;
    private String fileDesc;
    private String fileSize;
    private String extension;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "{" + "\"fileName\":\"" + fileName + "\""
                + ",\"createId\":\"" + createId + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"fileDesc\":\"" + fileDesc + "\""
                + ",\"createTime\":\"" + createTime + "\""
                + ",\"fileSize\":\"" + fileSize + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"extension\":\"" + extension + "\""
                + ",\"updateTime\":\"" + updateTime + "\""
                + ",\"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
