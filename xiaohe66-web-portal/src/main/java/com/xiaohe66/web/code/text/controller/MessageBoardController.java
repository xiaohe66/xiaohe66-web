package com.xiaohe66.web.code.text.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.sys.helper.SysCfgHelper;
import com.xiaohe66.web.code.text.dto.MessageBoardDto;
import com.xiaohe66.web.code.text.po.MessageBoard;
import com.xiaohe66.web.code.text.service.MessageBoardService;

/**
 * @author xh
 * @time 19-10-23 22:15
 */
@XhController("/text/messageBoard")
public class MessageBoardController extends BaseController<MessageBoardService, MessageBoard, MessageBoardDto> {

    private UserService userService;

    public MessageBoardController(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void checkSave(MessageBoard po) {
        // 不检查查询权限
    }

    @Override
    protected void checkSelect() {
        // 不检查查询权限
    }

    @Override
    protected Wrapper<MessageBoard> createPageQueryWrapper() {
        MessageBoard messageBoard = new MessageBoard();
        messageBoard.setUsrId(SysCfgHelper.findXhUsrId());
        return new QueryWrapper<>();
    }

    @Override
    protected void convertTask(MessageBoardDto dto, MessageBoard po) {
        String anonymity = po.getAnonymity();
        if (anonymity == null || anonymity.length() == 0) {
            User user = userService.getById(po.getCreateId());
            dto.setUsrName(user.getUsrName());
            dto.setImgFileId(user.getImgFileId());
        } else {
            dto.setUsrName(po.getAnonymity());
            dto.setImgFileId(1);
        }
    }
}
