package com.xiaohe66.web.common.base;

import java.io.Serializable;

/**
 * 基础实体类，所有的实体类都直接或间接继承该类
 *
 * @author xiaohe
 * @time 17-10-28 028
 */
public abstract class BasePo implements Serializable,BaseParam {
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
