package com.xiaohe66.web.code.text.dto;

import com.xiaohe66.web.base.base.BaseDtoDetailed;

/**
 * @author xh
 * @date 18-04-01 001
 */
public class MessageBoardDto extends BaseDtoDetailed{
    private String msg;
    private String usrName;
    private Integer imgFileId;
    private String anonymity;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public Integer getImgFileId() {
        return imgFileId;
    }

    public void setImgFileId(Integer imgFileId) {
        this.imgFileId = imgFileId;
    }

    public String getAnonymity() {
        return anonymity;
    }

    public void setAnonymity(String anonymity) {
        this.anonymity = anonymity;
    }
}
