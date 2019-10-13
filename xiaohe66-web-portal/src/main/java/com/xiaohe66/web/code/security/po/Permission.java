package com.xiaohe66.web.code.security.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePo;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@TableName("xiaohe66_web_security_permission")
public class Permission extends BasePo {

    private String name;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "{" + "\"name\":\"" + name + "\""
                + ",\"desc\":\"" + desc + "\""
                + ",\"id\":\"" + id + "\""
                + "}";
    }
}
