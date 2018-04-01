package com.xiaohe66.web.text.controller;

import com.xiaohe66.web.common.annotation.Get;
import com.xiaohe66.web.common.annotation.Page;
import com.xiaohe66.web.common.annotation.Paging;
import com.xiaohe66.web.common.annotation.Post;
import com.xiaohe66.web.common.annotation.XhController;
import com.xiaohe66.web.common.util.ClassUtils;
import com.xiaohe66.web.org.dto.UsrDto;
import com.xiaohe66.web.org.service.UsrService;
import com.xiaohe66.web.sys.dto.CurrentUsr;
import com.xiaohe66.web.sys.dto.Result;
import com.xiaohe66.web.text.dto.MessageBoardDto;
import com.xiaohe66.web.text.po.MessageBoard;
import com.xiaohe66.web.text.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author xh
 * @date 18-03-31 031
 */
@XhController("/text/messageBoard")
public class MessageBoardController {

    @Autowired
    private MessageBoardService messageBoardService;

    @Page("/index")
    public String index(Model model){
        return index(model,null);
    }

    @Page("/index/{usrId}")
    public String index(Model model,@PathVariable("usrId") Long usrId){
        model.addAttribute("usr",messageBoardService.lookAtUsr(usrId));
        List list = messageBoardService.findByUsrId(usrId);
        model.addAttribute("list",list);
        model.addAttribute("size",list.size());
        return "text/message_board";
    }

    @Post
    public Result add(CurrentUsr currentUsr,String msg,Long usrId){
        messageBoardService.add(msg,usrId,currentUsr.getId());
        return Result.ok();
    }

    @Paging
    @Get
    public Result get(Long usrId){
        return Result.ok(messageBoardService.findByUsrId(usrId));
    }
}
