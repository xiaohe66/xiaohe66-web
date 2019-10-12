package com.xiaohe66.web.code.love.po;

import com.xiaohe66.web.base.base.BasePoDetailed;

/**
 * @author xiaohe
 * @time 2019.10.11 10:51
 */
public class PhotoDesc extends BasePoDetailed {

    private Integer photoId;
    private String desc;

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "{" + "\"photoId\":\"" + photoId + "\""
                + ",\"desc\":\"" + desc + "\""
                + ",\"createId\":\"" + createId + "\""
                + ",\"createTime\":" + createTime
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"isDelete\":\"" + isDelete + "\""
                + ",\"id\":\"" + id + "\""
                + "}";
    }
}
