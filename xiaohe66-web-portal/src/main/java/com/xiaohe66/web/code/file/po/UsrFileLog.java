package com.xiaohe66.web.code.file.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePo;

import java.util.Date;

/**
 * @author xh
 * @date 18-04-15 015
 */
@TableName("xiaohe66_web_org_user_file_log")
public class UsrFileLog extends BasePo{

    private Integer createId;
    private Date createTime;
    private Integer logType;
    private Integer usrFileId;
    private String ip;

    public UsrFileLog(Integer usrFileId) {
        this.usrFileId = usrFileId;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
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

    public Integer getUsrFileId() {
        return usrFileId;
    }

    public void setUsrFileId(Integer usrFileId) {
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
