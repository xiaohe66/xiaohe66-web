package com.xiaohe66.web.text.param;

import com.xiaohe66.web.text.po.MessageBoard;

/**
 * @author xh
 * @date 18-04-01 001
 */
public class MessageBoardParam extends MessageBoard{

    public MessageBoardParam() {
    }

    public MessageBoardParam(Long usrId){
        super();
        super.usrId = usrId;
    }
}
