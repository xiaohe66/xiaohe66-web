package com.xiaohe66.web.org.controller;

import com.xiaohe66.web.org.service.UsrFileService;
import com.xiaohe66.web.common.annotation.Post;
import com.xiaohe66.web.common.annotation.XhController;
import com.xiaohe66.web.sys.dto.CurrentUsr;
import com.xiaohe66.web.sys.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xh
 * @date 18-03-12 012
 */
@XhController("/org/usr/file")
public class UsrFileController {

    @Autowired
    private UsrFileService usrFileService;

    @Post
    public Result add(CurrentUsr currentUsr,@RequestParam("file") MultipartFile file,String md5){
        return Result.ok(usrFileService.uploadFile(file,md5,currentUsr.getId()));
    }

}
