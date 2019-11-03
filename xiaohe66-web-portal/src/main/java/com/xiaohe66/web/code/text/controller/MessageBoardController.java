package com.xiaohe66.web.code.text.controller;

import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.code.org.dto.LookAtUserDto;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.text.dto.MessageBoardDto;
import com.xiaohe66.web.code.text.po.MessageBoard;
import com.xiaohe66.web.code.text.service.MessageBoardService;
import org.springframework.ui.Model;

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

    @Page("/index")
    public String index(Model model) {
        LookAtUserDto lookAtUser = userService.lookAtUser(null);
        model.addAttribute("lookAtUser", lookAtUser);
        model.addAttribute("title", lookAtUser.getUsrName() + "的留言板");
        model.addAttribute("userDivTitle", "站长");
        return "text/message_board";
    }

    @Override
    protected void checkSave(MessageBoard po) {
        // 未登录者，需要填写匿名名称
        if (!WebUtils.isLogin()) {
            Check.notEmpty(po);
        }
    }

    @Override
    protected void checkSelect(MessageBoard messageBoard) {
        // 不检查查询权限
    }

}
