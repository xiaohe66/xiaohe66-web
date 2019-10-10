package com.xiaohe66.web.code.text.po;

import com.xiaohe66.web.base.base.BasePoDetailed;

/**
 * @author xh
 * @date 18-04-01 001
 */
public class MessageBoard extends BasePoDetailed{
    protected Long usrId;
    protected String msg;

    /**
     * 匿名留言的用户名称
     */
    protected String anonymity;

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

    public String getAnonymity() {
        return anonymity;
    }

    public void setAnonymity(String anonymity) {
        this.anonymity = anonymity;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"usrId\":\"" + usrId + "\""
                + ",\"createTime\":" + createTime
                + ",\"msg\":\"" + msg + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"anonymity\":\"" + anonymity + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
