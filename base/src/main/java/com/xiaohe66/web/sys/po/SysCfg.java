package com.xiaohe66.web.sys.po;

import com.xiaohe66.web.common.base.BasePo;

/**
 * @author xiaohe
 * @time 17-11-07 007
 */
public class SysCfg extends BasePo {

    private String cfgKey;
    private String cfgVal;

    public String getCfgKey() {
        return cfgKey;
    }

    public void setCfgKey(String cfgKey) {
        this.cfgKey = cfgKey;
    }

    public String getCfgVal() {
        return cfgVal;
    }

    public void setCfgVal(String cfgVal) {
        this.cfgVal = cfgVal;
    }

    @Override
    public String toString() {
        return "{" + "\"cfgKey\":\"" + cfgKey + "\""
                + ", \"cfgVal\":\"" + cfgVal + "\""
                + ", \"id\":\"" + id + "\""
                + "}";
    }
}
