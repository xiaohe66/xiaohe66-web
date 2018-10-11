package com.xiaohe66.web.controller;

import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.file.service.UsrFileService;
import com.xiaohe66.web.org.service.UsrService;
import com.xiaohe66.web.resume.service.ResumeMainService;
import com.xiaohe66.web.sys.helper.SysCfgHelper;
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

    @Resource
    private ResumeMainService resumeMainService;

    @Page("/index")
    public String index(Model model){
        Long xhUsrId = SysCfgHelper.findXhUsrId();
        model.addAttribute("title","小何的简历");
        model.addAttribute("usrDivTitle","小何");
        model.addAttribute("usrDto",usrService.lookAtUsr(xhUsrId));
        model.addAttribute("fileList",usrFileService.findDtoHotTop5(xhUsrId));
        model.addAttribute("hotArticle",articleService.findDtoHotTop5(xhUsrId));
        model.addAttribute("resumeMain",resumeMainService.findDtoByUsrId(xhUsrId));
        return "resume/resume_index";
    }

}
