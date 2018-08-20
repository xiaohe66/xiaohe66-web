package com.xiaohe66.web.file.po;

import com.xiaohe66.web.base.base.BasePo;

import java.util.Date;

/**
 * @author xh
 * @date 18-03-22 022
 */
public class CommonFile extends BasePo{
    protected String md5;
    protected String fileUrl;
    protected Integer fileByte;
    protected Date startTime;
    protected Date endTime;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getFileByte() {
        return fileByte;
    }

    public void setFileByte(Integer fileByte) {
        this.fileByte = fileByte;
    }

    @Override
    public String toString() {
        return "{" + "\"md5\":\"" + md5 + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"fileUrl\":\"" + fileUrl + "\""
                + ",\"fileByte\":\"" + fileByte + "\""
                + ",\"startTime\":" + startTime
                + ",\"endTime\":" + endTime
                + "}";
    }
}
