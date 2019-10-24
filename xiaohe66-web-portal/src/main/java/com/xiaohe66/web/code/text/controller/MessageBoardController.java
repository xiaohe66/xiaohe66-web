package com.xiaohe66.web.code.text.controller;

import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.code.text.dto.MessageBoardDto;
import com.xiaohe66.web.code.text.po.MessageBoard;
import com.xiaohe66.web.code.text.service.MessageBoardService;

/**
 * @author xh
 * @time 19-10-23 22:15
 */
@XhController("/text/messageBoard")
public class MessageBoardController extends BaseController<MessageBoardService, MessageBoard, MessageBoardDto> {

    @Override
    protected void checkSave(MessageBoard po) {
        // 不检查查询权限
    }

    @Override
    protected void checkSelect() {
        // 不检查查询权限
    }

}
