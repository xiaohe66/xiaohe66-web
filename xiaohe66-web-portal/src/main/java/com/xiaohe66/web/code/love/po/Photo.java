package com.xiaohe66.web.code.love.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePoDetailed;

/**
 * @author xiaohe
 * @time 2019.10.11 09:46
 */
@TableName("xiaohe66_web_love_photo")
public class Photo extends BasePoDetailed {

    private Integer fileId;
    private String name;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" + "\"fileId\":\"" + fileId + "\""
                + ",\"name\":\"" + name + "\""
                + ",\"createId\":\"" + createId + "\""
                + ",\"createTime\":" + createTime
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"isDelete\":\"" + isDelete + "\""
                + ",\"id\":\"" + id + "\""
                + "}";
    }
}
