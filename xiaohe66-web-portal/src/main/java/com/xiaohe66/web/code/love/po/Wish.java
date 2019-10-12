package com.xiaohe66.web.code.love.po;

import com.xiaohe66.web.base.base.BasePoDetailed;

/**
 * @author xiaohe
 * @time 2019.10.11 10:54
 */
public class Wish extends BasePoDetailed {

    private String name;
    private String email;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" + "\"name\":\"" + name + "\""
                + ",\"email\":\"" + email + "\""
                + ",\"message\":\"" + message + "\""
                + ",\"createId\":\"" + createId + "\""
                + ",\"createTime\":" + createTime
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"isDelete\":\"" + isDelete + "\""
                + ",\"id\":\"" + id + "\""
                + "}";
    }
}
