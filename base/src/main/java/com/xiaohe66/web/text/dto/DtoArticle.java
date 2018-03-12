package com.xiaohe66.web.text.dto;

import com.xiaohe66.web.common.base.BaseDtoDetailed;

/**
 * @author xiaohe
 * @time 17-11-11 011
 */
public class DtoArticle extends BaseDtoDetailed{

    private String text;
    private String title;

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
        return "{" + "\"text\":\"" + text + "\""
                + ",\"createId\":\"" + createId + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"title\":\"" + title + "\""
                + ",\"createTime\":" + createTime
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
