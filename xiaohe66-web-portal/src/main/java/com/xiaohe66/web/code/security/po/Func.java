package com.xiaohe66.web.code.security.po;

import com.xiaohe66.web.base.base.BasePo;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
public class Func extends BasePo {

    private String funcName;
    private String funcDesc;

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncDesc() {
        return funcDesc;
    }

    public void setFuncDesc(String funcDesc) {
        this.funcDesc = funcDesc;
    }

    @Override
    public String toString() {
        return "{" + "\"funcName\":\"" + funcName + "\""
                + ", \"funcDesc\":\"" + funcDesc + "\""
                + ", \"id\":\"" + id + "\""
                + "}";
    }
}
