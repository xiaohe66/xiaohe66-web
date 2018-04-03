package com.xiaohe66.web.org.po;


import com.xiaohe66.web.common.base.BasePoDetailed;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
public class Usr extends BasePoDetailed {
    private String usrName;
    private String usrPwd;
    private String signature;
    private Long imgFileId;

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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getImgFileId() {
        return imgFileId;
    }

    public void setImgFileId(Long imgFileId) {
        this.imgFileId = imgFileId;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"usrName\":\"" + usrName + "\""
                + ",\"createTime\":" + createTime
                + ",\"usrPwd\":\"" + usrPwd + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"signature\":\"" + signature + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"imgFileId\":\"" + imgFileId + "\""
                + ",\"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
