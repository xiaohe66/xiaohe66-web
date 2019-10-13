package com.xiaohe66.web.code.file.controller;

import com.xiaohe66.web.base.annotation.Del;
import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Paging;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.code.file.dto.UsrFileDto;
import com.xiaohe66.web.code.file.service.UsrFileService;
import com.xiaohe66.web.code.org.helper.UserHelper;
import com.xiaohe66.web.code.org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/**
 * @author xh
 * @date 18-03-12 012
 */
@XhController("/org/usr/file")
public class UsrFileController {

    @Autowired
    private UsrFileService usrFileService;

    @Autowired
    private UserService userService;

    @Paging
    @Get("/all/{onlyWebmaster}")
    public List<UsrFileDto> allData(@PathVariable("onlyWebmaster") boolean onlyWebmaster){
        return usrFileService.findDtoAll(null,onlyWebmaster);
    }

    @Paging
    @Get("/all/{onlyWebmaster}/{search}")
    public List<UsrFileDto> allData2(@PathVariable("onlyWebmaster") boolean onlyWebmaster,@PathVariable("search") String search){
        return usrFileService.findDtoAll(search,onlyWebmaster);
    }

    @Paging
    @Get
    public List<UsrFileDto> page(){
        return usrFileService.findDtoByUsrId(UserHelper.getCurrentUsrId());
    }

    @Post
    public Set<Integer> uploadFilePrepare( String md5, Float mb, String fileName, String extension){
        return usrFileService.uploadDefaultFilePrepare(UserHelper.getCurrentUsrId(),md5,mb,fileName,extension);
    }

    @Put("/{id}")
    public void update(@PathVariable("id")Integer id,String fileName){
        usrFileService.updateNameById(id,fileName, UserHelper.getCurrentUsrId());
    }

    @Del("/{id}")
    public void del(@PathVariable("id")Integer id){
        usrFileService.removeById(id);
    }

    @Post("/head")
    public Integer uploadHeadImg(@RequestParam("file") MultipartFile file, String md5){
        Integer imgFileId =  usrFileService.uploadImg(file,md5, UserHelper.getCurrentUsrId(),UsrFileService.USR_HEAD_IMG_FILE_TYPE);
        userService.updateImgFile(imgFileId);
        return imgFileId;
    }

    @Post("/article")
    public Integer uploadArticleImg(@RequestParam("file") MultipartFile file, String md5){
        return usrFileService.uploadImg(file,md5, UserHelper.getCurrentUsrId(),UsrFileService.USR_ARTICLE_IMG_FILE_TYPE);
    }

}
