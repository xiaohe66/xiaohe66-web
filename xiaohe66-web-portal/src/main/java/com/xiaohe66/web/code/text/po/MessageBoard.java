package com.xiaohe66.web.code.text.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePoDetailed;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xh
 * @date 18-04-01 001
 */
@TableName("xiaohe66_web_text_message_board")
@Getter
@Setter
public class MessageBoard extends BasePoDetailed {
    protected Integer userId;
    protected String msg;

    /**
     * 匿名留言的用户名称
     */
    protected String anonymity;

    protected String contact;

    public MessageBoard() {
    }

    public MessageBoard(Integer userId, String msg) {
        this.userId = userId;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"userId\":\"" + userId + "\""
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
