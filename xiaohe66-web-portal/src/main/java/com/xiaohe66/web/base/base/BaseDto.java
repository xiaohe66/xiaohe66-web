package com.xiaohe66.web.base.base;

import java.io.Serializable;

/**
 * 基础数据传输类，所有的数据传输类都直接或间接继承该类
 *
 * @author xiaohe
 * @time 17-10-28 028
 */
public abstract class BaseDto implements Serializable {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "{" + "\"id\":\"" + id + "\""
                + "}";
    }
}
