package com.xiaohe66.web.org.po;

import com.xiaohe66.web.common.base.BasePoDetailed;

/**
 * @author xiaohe
 * @time 17-11-11 011
 */
public class UsrFile extends BasePoDetailed {

    private String fileName;
    private String fileDesc;
    private String fileType;
    private Long fileId;

    public UsrFile(){}

    public UsrFile(Long id,String fileName) {
        super.id = id;
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
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

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"fileName\":\"" + fileName + "\""
                + ",\"createTime\":" + createTime
                + ",\"fileDesc\":\"" + fileDesc + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"fileType\":\"" + fileType + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"fileId\":\"" + fileId + "\""
                + ",\"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
