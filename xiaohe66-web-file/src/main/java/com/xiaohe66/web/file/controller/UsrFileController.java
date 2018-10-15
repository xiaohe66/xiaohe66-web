package com.xiaohe66.web.file.controller;

import com.xiaohe66.web.base.annotation.Del;
import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Paging;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.file.dto.UsrFileDto;
import com.xiaohe66.web.file.service.UsrFileService;
import com.xiaohe66.web.org.helper.UsrHelper;
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
        return usrFileService.findDtoByUsrId(UsrHelper.getCurrentUsrId());
    }

    @Post
    public Set<Integer> uploadFilePrepare( String md5, Float mb, String fileName, String extension){
        return usrFileService.uploadDefaultFilePrepare(UsrHelper.getCurrentUsrId(),md5,mb,fileName,extension);
    }

    @Put("/{id}")
    public void update(@PathVariable("id")Long id,String fileName){
        usrFileService.updateNameById(id,fileName, UsrHelper.getCurrentUsrId());
    }

    @Del("/{id}")
    public void del(@PathVariable("id")Long id){
        usrFileService.delById(id,UsrHelper.getCurrentUsrId());
    }


    @Post("/head")
    public Long uploadHeadImg(@RequestParam("file") MultipartFile file, String md5){
        return usrFileService.uploadImg(file,md5,UsrHelper.getCurrentUsrId(),UsrFileService.USR_HEAD_IMG_FILE_TYPE);
    }

    @Post("/article")
    public Long uploadArticleImg(@RequestParam("file") MultipartFile file, String md5){
        return usrFileService.uploadImg(file,md5,UsrHelper.getCurrentUsrId(),UsrFileService.USR_ARTICLE_IMG_FILE_TYPE);
    }

}
