package com.xiaohe66.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.code.file.dto.UsrFileDto;
import com.xiaohe66.web.code.file.service.UsrFileService;
import com.xiaohe66.web.code.org.helper.UsrHelper;
import com.xiaohe66.web.code.org.service.UsrService;
import com.xiaohe66.web.code.text.service.ArticleService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author xh
 * @date 18-03-12 012
 */
@XhController("/org/usr/file")
public class UsrFilePageController {

    private static final String USR_FILE_INDEX_PAGE_URL = "org/file_index";
    private static final String USR_FILE_ADMIN_PAGE_URL = "org/file_admin";
    private static final String USR_FILE_ALL_PAGE_URL = "org/file_all";

    @Resource
    private UsrFileService usrFileService;

    @Resource
    private UsrService usrService;

    @Resource
    private ArticleService articleService;

    @Page("/admin")
    public String admin(Model model){

        PageHelper.startPage(1,10);
        List<UsrFileDto> list = usrFileService.findDtoByUsrId(UsrHelper.getCurrentUsrId());
        model.addAttribute("pageInfo",new PageInfo<>(list));
        model.addAttribute("title","文件管理");
        model.addAttribute("size",list.size());
        model.addAttribute("page",USR_FILE_ADMIN_PAGE_URL);

        return OtherPageController.USR_ZONE_PAGE_URL;
    }

    @Page("/index")
    public String index(Model model){
        model.addAttribute("page",USR_FILE_INDEX_PAGE_URL);
        model.addAttribute("usrDto",usrService.lookAtUsr(null));

        return OtherPageController.RIGHT_PAGE_URL;
    }

    @Page("/all")
    public String all(Model model){
        PageHelper.startPage(1,20);
        model.addAttribute("pageInfo",new PageInfo<>(usrFileService.findDtoAll(null,false)));
        model.addAttribute("usrDto",usrService.lookAtUsr(null));
        model.addAttribute("title","资源列表");
        model.addAttribute("usrDivTitle","站长");
        model.addAttribute("fileList",usrFileService.findDtoHotTop5(null));
        model.addAttribute("hotArticle",articleService.findDtoHotTop5(null));
        model.addAttribute("page",USR_FILE_ALL_PAGE_URL);
        return OtherPageController.RIGHT_PAGE_URL;
    }


    @Page("/img/{id}")
    public void showImg(HttpServletResponse response, @PathVariable("id")Integer id){
        usrFileService.showImg(response,id);
    }

    @Page("/download/{id}")
    public void download(HttpServletResponse response,@PathVariable("id")Integer id){
        usrFileService.downloadFile(response,id,UsrHelper.getCurrentUsrId());
    }

}
