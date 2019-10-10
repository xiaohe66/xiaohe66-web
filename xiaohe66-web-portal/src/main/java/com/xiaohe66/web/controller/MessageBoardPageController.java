package com.xiaohe66.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.code.file.service.UsrFileService;
import com.xiaohe66.web.code.org.dto.UsrDto;
import com.xiaohe66.web.code.org.service.UsrService;
import com.xiaohe66.web.code.text.dto.MessageBoardDto;
import com.xiaohe66.web.code.text.service.ArticleService;
import com.xiaohe66.web.code.text.service.MessageBoardService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xh
 * @date 18-03-31 031
 */
@XhController("/text/messageBoard")
public class MessageBoardPageController {

    @Resource
    private MessageBoardService messageBoardService;

    @Resource
    private UsrService usrService;

    @Resource
    private UsrFileService usrFileService;

    @Resource
    private ArticleService articleService;

    @Page("/index")
    public String index(Model model){
        return index(model,null);
    }

    @Page("/index/{usrId}")
    public String index(Model model,@PathVariable("usrId") Long usrId){
        //todo:目前只能给站长留言，以后开放所有用户的留言板后再删除掉这行代码
        usrId = null;
        UsrDto usrDto = usrService.lookAtUsr(usrId);
        model.addAttribute("usrDto",usrDto);
        PageHelper.startPage(1,10);
        List<MessageBoardDto> list = messageBoardService.findByUsrId(usrId);
        model.addAttribute("pageInfo",new PageInfo<>(list));
        model.addAttribute("size",list.size());
        model.addAttribute("title",usrDto.getUsrName()+"的留言板");
        model.addAttribute("usrDivTitle","站长");
        model.addAttribute("page","text/message_board");
        model.addAttribute("fileList",usrFileService.findDtoHotTop5(usrId));
        model.addAttribute("hotArticle",articleService.findDtoHotTop5(usrId));
        return OtherPageController.RIGHT_PAGE_URL;
    }

}
