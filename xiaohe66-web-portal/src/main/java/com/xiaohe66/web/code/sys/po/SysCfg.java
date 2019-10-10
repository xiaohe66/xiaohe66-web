package com.xiaohe66.web.code.sys.po;

import com.xiaohe66.web.base.base.BasePoDetailed;

/**
 * @author xiaohe
 * @time 17-11-07 007
 */
public class SysCfg extends BasePoDetailed {

    private String cfgKey;
    private String cfgVal;
    private String cfgDesc;

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

    public String getCfgDesc() {
        return cfgDesc;
    }

    public void setCfgDesc(String cfgDesc) {
        this.cfgDesc = cfgDesc;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ", \"cfgKey\":\"" + cfgKey + "\""
                + ", \"createTime\":" + createTime
                + ", \"cfgVal\":\"" + cfgVal + "\""
                + ", \"id\":\"" + id + "\""
                + ", \"updateId\":\"" + updateId + "\""
                + ", \"cfgDesc\":\"" + cfgDesc + "\""
                + ", \"updateTime\":" + updateTime
                + ", \"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
