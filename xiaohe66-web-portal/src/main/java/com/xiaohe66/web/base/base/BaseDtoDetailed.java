package com.xiaohe66.web.base.base;

/**
 * 基础数据传输类，所有的数据传输类都直接或间接继承该类
 *
 * @author xiaohe
 * @time 17-10-28 028
 */
public abstract class BaseDtoDetailed extends BaseDto {
    protected Integer createId;
    protected String createTime;
    protected Integer updateId;
    protected String updateTime;
    protected Boolean isDelete;

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
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
                + ", \"id\":\"" + id + "\""
                + ", \"createTime\":" + createTime
                + ", \"updateId\":\"" + updateId + "\""
                + ", \"updateTime\":" + updateTime
                + ", \"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
