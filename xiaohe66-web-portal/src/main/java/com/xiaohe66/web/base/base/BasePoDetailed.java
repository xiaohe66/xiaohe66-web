package com.xiaohe66.web.base.base;

import java.util.Date;

/**
 * @author xiaohe
 * @time 17-11-08 008
 */
public abstract class BasePoDetailed extends BasePo {
    protected Integer createId;
    protected Date createTime;
    protected Integer updateId;
    protected Date updateTime;
    protected Boolean isDelete;

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

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ", \"createTime\":" + createTime
                + ", \"id\":\"" + id + "\""
                + ", \"updateId\":\"" + updateId + "\""
                + ", \"updateTime\":" + updateTime
                + ", \"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
