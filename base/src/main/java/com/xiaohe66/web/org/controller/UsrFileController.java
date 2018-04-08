package com.xiaohe66.web.org.controller;

import com.xiaohe66.web.common.annotation.Page;
import com.xiaohe66.web.common.annotation.Post;
import com.xiaohe66.web.common.annotation.XhController;
import com.xiaohe66.web.org.service.UsrFileService;
import com.xiaohe66.web.sys.controller.PageController;
import com.xiaohe66.web.sys.dto.CurrentUsr;
import com.xiaohe66.web.sys.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xh
 * @date 18-03-12 012
 */
@XhController("/org/usr/file")
public class UsrFileController {

    private static final String USR_FILE_LIST_PAGE_URL = "org/file_list";

    @Autowired
    private UsrFileService usrFileService;

    @Page("/index")
    public String index(Model model,CurrentUsr currentUsr){

        model.addAttribute("page",USR_FILE_LIST_PAGE_URL);
        model.addAttribute("list",usrFileService.findDtoByUsrId(currentUsr.getId()));

        return PageController.USR_ZONE_PAGE_URL;
    }


    @Post
    public Result add(CurrentUsr currentUsr,@RequestParam("file") MultipartFile file,String md5){
        return Result.ok(usrFileService.uploadFile(file,md5,currentUsr.getId()));
    }

}
