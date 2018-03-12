package com.xiaohe66.web.text.po;

import com.xiaohe66.web.common.base.BasePoDetailed;

/**
 * @author xiaohe
 * @time 17-11-08 008
 */
public class Article extends BasePoDetailed {

    private String text;
    private String title;
    private Integer publishState;
    private Integer secretLevel;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublishState() {
        return publishState;
    }

    public void setPublishState(Integer publishState) {
        this.publishState = publishState;
    }

    public Integer getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(Integer secretLevel) {
        this.secretLevel = secretLevel;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ", \"text\":\"" + text + "\""
                + ", \"createTime\":" + createTime
                + ", \"title\":\"" + title + "\""
                + ", \"id\":\"" + id + "\""
                + ", \"updateId\":\"" + updateId + "\""
                + ", \"publishState\":\"" + publishState + "\""
                + ", \"updateTime\":" + updateTime
                + ", \"secretLevel\":\"" + secretLevel + "\""
                + ", \"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
