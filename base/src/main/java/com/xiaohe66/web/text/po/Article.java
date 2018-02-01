package com.xiaohe66.web.text.po;

import com.xiaohe66.web.common.base.BasePoDetailed;

/**
 * @author xiaohe
 * @time 17-11-08 008
 */
public class Article extends BasePoDetailed {

    private String text;
    private String title;
    private Integer isPrivate;
    private Boolean isPublish;

    public Integer getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Integer isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Boolean getPublish() {
        return isPublish;
    }

    public void setPublish(Boolean publish) {
        isPublish = publish;
    }

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

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ", \"text\":\"" + text + "\""
                + ", \"createTime\":" + createTime
                + ", \"title\":\"" + title + "\""
                + ", \"id\":\"" + id + "\""
                + ", \"updateId\":\"" + updateId + "\""
                + ", \"isPrivate\":\"" + isPrivate + "\""
                + ", \"updateTime\":" + updateTime
                + ", \"isPublish\":\"" + isPublish + "\""
                + ", \"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
