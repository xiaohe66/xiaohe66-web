package com.xiaohe66.web.text.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Paging;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.text.dto.MessageBoardDto;
import com.xiaohe66.web.text.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Post
    public void add(String msg, Long usrId,String anonymity){
        //todo:目前只能给站长留言，以后开放所有用户的留言板后再删除掉这行代码
        usrId = Final.Sys.XIAO_HE_USR_ID;
        messageBoardService.add(msg,usrId,anonymity);
    }

    @Paging
    @Get("/{usrId}")
    public List<MessageBoardDto> get(@PathVariable("usrId") Long usrId){
        //todo:目前只能给站长留言，以后开放所有用户的留言板后再删除掉这行代码
        usrId = null;
        return messageBoardService.findByUsrId(usrId);
    }
}
