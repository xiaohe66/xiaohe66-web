package com.xiaohe66.web.code.file.po;

import com.xiaohe66.web.base.base.BasePoDetailed;

/**
 * @author xiaohe
 * @time 17-11-11 011
 */
public class UsrFile extends BasePoDetailed {

    private String fileName;
    private String fileDesc;
    private Integer fileType;
    private Long fileId;
    private String extension;

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

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
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
                + ",\"extension\":\"" + extension + "\""
                + "}";
    }
}
