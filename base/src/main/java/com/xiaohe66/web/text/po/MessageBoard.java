package com.xiaohe66.web.text.po;

import com.xiaohe66.web.common.base.BasePoDetailed;

/**
 * @author xh
 * @date 18-04-01 001
 */
public class MessageBoard extends BasePoDetailed{
    protected Long usrId;
    protected String msg;

    public MessageBoard(){}

    public MessageBoard(Long usrId, String msg) {
        this.usrId = usrId;
        this.msg = msg;
    }

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"usrId\":\"" + usrId + "\""
                + ",\"createTime\":" + createTime
                + ",\"msg\":\"" + msg + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
