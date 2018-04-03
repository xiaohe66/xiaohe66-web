package com.xiaohe66.web.org.dto;

import com.xiaohe66.web.common.base.BaseDto;
import com.xiaohe66.web.org.po.Usr;

/**
 * @author xiaohe
 * @time 17-11-01 001
 */
public class UsrDto extends BaseDto {

    private String usrName;
    private String signature;
    private String imgUrl;

    public UsrDto(){

    }
    public UsrDto(Usr usr){
        id = usr.getId();
        usrName = usr.getUsrName();
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "{" + "\"usrName\":\"" + usrName + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"signature\":\"" + signature + "\""
                + ",\"imgUrl\":\"" + imgUrl + "\""
                + "}";
    }
}
