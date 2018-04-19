package com.xiaohe66.web.text.dto;

import com.xiaohe66.web.common.base.BaseDtoDetailed;

/**
 * @author xh
 * @date 18-04-01 001
 */
public class MessageBoardDto extends BaseDtoDetailed{
    private String msg;
    private String usrName;
    private Long imgFileId;

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

    public Long getImgFileId() {
        return imgFileId;
    }

    public void setImgFileId(Long imgFileId) {
        this.imgFileId = imgFileId;
    }
}
