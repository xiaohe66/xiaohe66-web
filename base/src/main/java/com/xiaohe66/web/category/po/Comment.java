package com.xiaohe66.web.category.po;


import com.xiaohe66.web.common.base.BasePoDetailed;

/**
 * @author xiaohe
 * @time 17-11-11 011
 */
public class Comment extends BasePoDetailed {

    private Long cId;
    private String text;

    public Long getCId() {
        return cId;
    }

    public void setCId(Long cId) {
        this.cId = cId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ", \"cId\":\"" + cId + "\""
                + ", \"createTime\":" + createTime
                + ", \"text\":\"" + text + "\""
                + ", \"id\":\"" + id + "\""
                + ", \"updateId\":\"" + updateId + "\""
                + ", \"updateTime\":" + updateTime
                + ", \"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
