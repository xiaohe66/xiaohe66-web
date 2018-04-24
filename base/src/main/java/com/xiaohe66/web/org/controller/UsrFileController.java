package com.xiaohe66.web.org.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaohe66.web.common.annotation.Del;
import com.xiaohe66.web.common.annotation.Get;
import com.xiaohe66.web.common.annotation.Page;
import com.xiaohe66.web.common.annotation.Paging;
import com.xiaohe66.web.common.annotation.Post;
import com.xiaohe66.web.common.annotation.Put;
import com.xiaohe66.web.common.annotation.XhController;
import com.xiaohe66.web.org.dao.UsrFileDao;
import com.xiaohe66.web.org.dto.UsrFileDto;
import com.xiaohe66.web.org.po.UsrFile;
import com.xiaohe66.web.org.service.UsrFileService;
import com.xiaohe66.web.org.service.UsrService;
import com.xiaohe66.web.sys.controller.PageController;
import com.xiaohe66.web.sys.dto.CurrentUsr;
import com.xiaohe66.web.sys.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author xh
 * @date 18-03-12 012
 */
@XhController("/org/usr/file")
public class UsrFileController {

    private static final String USR_FILE_INDEX_PAGE_URL = "org/file_index";
    private static final String USR_FILE_ADMIN_PAGE_URL = "org/file_admin";

    @Autowired
    private UsrFileService usrFileService;

    @Autowired
    private UsrService usrService;

    @Page("/admin")
    public String admin(Model model,CurrentUsr currentUsr){

        PageHelper.startPage(1,10);
        List<UsrFileDto> list = usrFileService.findDtoByUsrId(currentUsr.getId());
        model.addAttribute("pageInfo",new PageInfo<>(list));
        model.addAttribute("size",list.size());
        model.addAttribute("page",USR_FILE_ADMIN_PAGE_URL);

        return PageController.USR_ZONE_PAGE_URL;
    }

    @Page("/index")
    public String index(Model model,CurrentUsr currentUsr){
        model.addAttribute("page",USR_FILE_INDEX_PAGE_URL);
        model.addAttribute("usrDto",usrService.lookAtUsr(null));

        return PageController.RIGHT_PAGE_URL;
    }


    @Page("/img/{id}")
    public void showImg(HttpServletResponse response, @PathVariable("id")Long id){
        usrFileService.showImg(response,id);
    }

    @Page("/download/{id}")
    public void download(HttpServletResponse response,CurrentUsr currentUsr,@PathVariable("id")Long id){
        usrFileService.downloadFile(response,id,currentUsr.getId());
    }

    @Paging
    @Get
    public Result page(CurrentUsr currentUsr){
        return Result.ok(usrFileService.findDtoByUsrId(currentUsr.getId()));
    }

    @Post
    public Result add(CurrentUsr currentUsr, @RequestParam("file") MultipartFile file,String md5){
        return Result.ok(usrFileService.uploadUsrFile(file,md5,currentUsr.getId()));
    }

    @Put("/{id}")
    public Result update(CurrentUsr currentUsr,@PathVariable("id")Long id,String fileName){
        usrFileService.updateNameById(id,fileName,currentUsr.getId());
        return Result.ok();
    }

    @Del("/{id}")
    public Result del(CurrentUsr currentUsr,@PathVariable("id")Long id){
        usrFileService.delById(id,currentUsr.getId());
        return Result.ok();
    }

}
