package com.xiaohe66.web.org.dto;

import com.xiaohe66.web.common.base.BaseDtoDetailed;

/**
 * @author xh
 * @date 18-04-08 008
 */
public class UsrFileDto extends BaseDtoDetailed{
    private String fileName;
    private String fileDesc;

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

    @Override
    public String toString() {
        return "{" + "\"fileName\":\"" + fileName + "\""
                + ",\"createId\":\"" + createId + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"fileDesc\":\"" + fileDesc + "\""
                + ",\"createTime\":\"" + createTime + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"updateTime\":\"" + updateTime + "\""
                + ",\"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
