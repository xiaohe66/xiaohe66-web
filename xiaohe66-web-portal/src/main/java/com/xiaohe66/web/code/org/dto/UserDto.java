package com.xiaohe66.web.code.org.dto;

import com.xiaohe66.web.base.base.BaseDto;
import com.xiaohe66.web.code.org.po.User;

/**
 * todo : 该类当前是用于session保存和返回客户端的，需要拆分成两个类
 *
 * @author xiaohe
 * @time 17-11-01 001
 */
public class UserDto extends BaseDto {

    private String usrName;
    private String signature;
    private Integer imgFileId;
    private String email;

    public UserDto(){

    }
    public UserDto(User user){
        id = user.getId();
        usrName = user.getUsrName();
        imgFileId = user.getImgFileId();
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

    public Integer getImgFileId() {
        return imgFileId;
    }

    public void setImgFileId(Integer imgFileId) {
        this.imgFileId = imgFileId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "{" + "\"usrName\":\"" + usrName + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"signature\":\"" + signature + "\""
                + ",\"imgFileId\":\"" + imgFileId + "\""
                + "}";
    }
}
