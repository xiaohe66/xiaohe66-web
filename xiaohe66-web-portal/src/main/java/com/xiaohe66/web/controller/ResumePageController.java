package com.xiaohe66.web.controller;

import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.resume.service.ResumeMainService;
import com.xiaohe66.web.code.sys.helper.SysCfgHelper;
import com.xiaohe66.web.code.text.service.ArticleService;
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
    private UserService userService;

    @Resource
    private ResumeMainService resumeMainService;

    @Page
    public String ind(Model model){
        return index(model);
    }

    @Page("/index")
    public String index(Model model){
        Integer xhUsrId = SysCfgHelper.findXhUsrId();
        model.addAttribute("title","小何的简历");
        model.addAttribute("usrDivTitle","小何");
        model.addAttribute("usrDto",userService.lookAtUser(xhUsrId));
        model.addAttribute("hotArticle",articleService.findDtoHotTop5(xhUsrId));
        model.addAttribute("resumeMain",resumeMainService.findDtoByUsrId(xhUsrId));
        return "resume/resume_index";
    }

}
