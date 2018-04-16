package com.xiaohe66.web.org.po;

import com.xiaohe66.web.common.base.BasePo;

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

    public UsrFileLog(Long createId, Date createTime, Long usrFileId) {
        this.createId = createId;
        this.createTime = createTime;
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

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"createTime\":" + createTime
                + ",\"logType\":\"" + logType + "\""
                + ",\"usrFileId\":\"" + usrFileId + "\""
                + "}";
    }
}
