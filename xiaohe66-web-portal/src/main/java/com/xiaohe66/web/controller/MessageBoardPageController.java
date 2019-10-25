package com.xiaohe66.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.code.file.service.UserFileService;
import com.xiaohe66.web.code.org.dto.LookAtUserDto;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.text.dto.MessageBoardDto;
import com.xiaohe66.web.code.text.service.ArticleService;
import com.xiaohe66.web.code.text.service.MessageBoardService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @author xh
 * @date 18-03-31 031
 */
@XhController("/text/messageBoard")
public class MessageBoardPageController {

    @Resource
    private MessageBoardService messageBoardService;

    @Resource
    private UserService userService;

    @Resource
    private UserFileService usrFileService;

    @Resource
    private ArticleService articleService;

    @Page("/index")
    public String index(Model model) {
        return index(model, null);
    }

    @Page("/index/{usrId}")
    public String index(Model model, @PathVariable("usrId") Integer usrId) {
        //todo:目前只能给站长留言，以后开放所有用户的留言板后再删除掉这行代码
        usrId = null;
        LookAtUserDto usrDto = userService.lookAtUser(usrId);
        model.addAttribute("usrDto", usrDto);
        IPage<MessageBoardDto> page = messageBoardService.pageData();
        model.addAttribute("page", page);
//        model.addAttribute("size", list.size());
        model.addAttribute("title", usrDto.getUsrName() + "的留言板");
        model.addAttribute("usrDivTitle", "站长");
//        model.addAttribute("page", "text/message_board");
//        model.addAttribute("fileList", usrFileService.findDtoHotTop5(usrId));
//        model.addAttribute("hotArticle", articleService.findDtoHotTop5(usrId));
        return "text/message_board";
    }

}
