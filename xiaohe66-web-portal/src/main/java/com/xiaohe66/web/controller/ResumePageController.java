package com.xiaohe66.web.controller;

import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.file.service.UsrFileService;
import com.xiaohe66.web.org.helper.UsrHelper;
import com.xiaohe66.web.org.service.UsrService;
import com.xiaohe66.web.security.service.SecurityService;
import com.xiaohe66.web.text.dto.ArticleDto;
import com.xiaohe66.web.text.service.ArticleService;
import org.springframework.ui.Model;

import javax.annotation.Resource;

/**
 * @author xh
 * @version 1.0
 * @time 2018-09-06 17:02
 */
@XhController("/resume")
public class ResumePageController {

    @Resource
    private ArticleService articleService;

    @Resource
    private UsrFileService usrFileService;

    @Resource
    private UsrService usrService;

    @Page("/index")
    public String index(Model model){
        Long currentUsrId = UsrHelper.getCurrentUsrId();
        model.addAttribute("title","小何的简历-xiaohe66");
        model.addAttribute("page","resume/resume_index");
        model.addAttribute("usrDto",usrService.lookAtUsr(currentUsrId));
        model.addAttribute("fileList",usrFileService.findDtoHotTop5(currentUsrId));
        model.addAttribute("hotArticle",articleService.findDtoHotTop5(currentUsrId));
        return OtherPageController.RIGHT_PAGE_URL;
    }

}
