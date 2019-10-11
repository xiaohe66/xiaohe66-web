package com.xiaohe66.web.code.text.po;

import com.xiaohe66.web.base.base.BasePo;

import java.util.Date;

/**
 * @author xh
 * @date 18-04-16 016
 */
public class ArticleLog extends BasePo{
    private Integer createId;
    private Date createTime;
    private Integer logType;
    private Integer articleId;
    private String ip;

    public ArticleLog(Integer articleId) {
        this.articleId = articleId;
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

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
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
                + ",\"articleId\":\"" + articleId + "\""
                + ",\"ip\":\"" + ip + "\""
                + "}";
    }
}
