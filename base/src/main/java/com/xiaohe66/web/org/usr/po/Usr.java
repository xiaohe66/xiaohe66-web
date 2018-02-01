package com.xiaohe66.web.org.usr.po;


import com.xiaohe66.web.common.base.BasePoDetailed;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
public class Usr extends BasePoDetailed {
    private String usrName;
    private String usrPwd;

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrPwd() {
        return usrPwd;
    }

    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ", \"usrName\":\"" + usrName + "\""
                + ", \"createTime\":" + createTime
                + ", \"usrPwd\":\"" + usrPwd + "\""
                + ", \"id\":\"" + id + "\""
                + ", \"updateId\":\"" + updateId + "\""
                + ", \"updateTime\":" + updateTime
                + ", \"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
