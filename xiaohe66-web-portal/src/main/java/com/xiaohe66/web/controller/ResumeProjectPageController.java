package com.xiaohe66.web.controller;

import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.code.org.service.UsrService;
import com.xiaohe66.web.code.resume.service.ResumeProjectService;
import com.xiaohe66.web.code.sys.helper.SysCfgHelper;
import com.xiaohe66.web.code.text.service.ArticleService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @author xh
 * @date 18-10-12 012
 */
@XhController("/resume/project")
public class ResumeProjectPageController {

    @Resource
    private ArticleService articleService;

    @Resource
    private UsrService usrService;

    @Resource
    private ResumeProjectService resumeProjectService;

    @Page("/{resumeId}")
    public String index(Model model, @PathVariable("resumeId") Long resumeId){
        Long xhUsrId = SysCfgHelper.findXhUsrId();
        model.addAttribute("title","小何的简历-项目详情");
        model.addAttribute("usrDivTitle","小何");
        model.addAttribute("usrDto",usrService.lookAtUsr(xhUsrId));
        model.addAttribute("hotArticle",articleService.findDtoHotTop5(xhUsrId));
        model.addAttribute("resumeProject",resumeProjectService.findDtoByResumeId(resumeId));
        return "resume/resume_project_view";
    }
}
