package com.xiaohe66.web.code.file.po;

import com.xiaohe66.web.base.base.BasePo;

import java.util.Date;

/**
 * @author xh
 * @date 18-04-15 015
 */
public class UsrFileLog extends BasePo{

    private Long createId;
    private Date createTime;
    private Integer logType;
    private Long usrFileId;
    private String ip;

    public UsrFileLog(Long usrFileId) {
        this.usrFileId = usrFileId;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public Long getUsrFileId() {
        return usrFileId;
    }

    public void setUsrFileId(Long usrFileId) {
        this.usrFileId = usrFileId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"createTime\":" + createTime
                + ",\"logType\":\"" + logType + "\""
                + ",\"usrFileId\":\"" + usrFileId + "\""
                + ",\"ip\":\"" + ip + "\""
                + "}";
    }
}
