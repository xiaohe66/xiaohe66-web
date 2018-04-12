package com.xiaohe66.web.org.dto;

import com.xiaohe66.web.common.base.BaseDtoDetailed;

/**
 * @author xh
 * @date 18-04-08 008
 */
public class UsrFileDto extends BaseDtoDetailed{
    private String fileName;
    private String fileDesc;
    private String fileType;
    private String fileSize;

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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "{" + "\"fileName\":\"" + fileName + "\""
                + ",\"createId\":\"" + createId + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"fileDesc\":\"" + fileDesc + "\""
                + ",\"createTime\":\"" + createTime + "\""
                + ",\"fileType\":\"" + fileType + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"fileSize\":\"" + fileSize + "\""
                + ",\"updateTime\":\"" + updateTime + "\""
                + ",\"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
