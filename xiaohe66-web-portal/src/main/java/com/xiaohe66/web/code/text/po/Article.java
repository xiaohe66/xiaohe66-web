package com.xiaohe66.web.code.text.po;

import com.xiaohe66.web.base.base.BasePoDetailed;

/**
 * @author xiaohe
 * @time 17-11-08 008
 */
public class Article extends BasePoDetailed {

    private String text;
    private String title;
    private Integer secretLevel;
    private Integer sysCategoryId;
    private Boolean isPublish;

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

    public Boolean getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Boolean publish) {
        isPublish = publish;
    }

    public Integer getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(Integer secretLevel) {
        this.secretLevel = secretLevel;
    }

    public Integer getSysCategoryId() {
        return sysCategoryId;
    }

    public void setSysCategoryId(Integer sysCategoryId) {
        this.sysCategoryId = sysCategoryId;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"text\":\"" + text + "\""
                + ",\"createTime\":" + createTime
                + ",\"title\":\"" + title + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"secretLevel\":\"" + secretLevel + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"sysCategoryId\":\"" + sysCategoryId + "\""
                + ",\"isDelete\":\"" + isDelete + "\""
                + ",\"isPublish\":\"" + isPublish + "\""
                + "}";
    }
}
