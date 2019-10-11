package com.xiaohe66.web.code.resume.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.file.service.UsrFileService;
import com.xiaohe66.web.code.resume.dao.ResumeMainDao;
import com.xiaohe66.web.code.resume.dto.ResumeMainDto;
import com.xiaohe66.web.code.resume.po.ResumeMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xh
 * @date 18-10-09 009
 */
@Service
public class ResumeMainService extends AbstractService<ResumeMain>{

    private static final Logger LOG = LoggerFactory.getLogger(ResumeMainService.class);

    private ResumeMainDao resumeMainDao;

    @Autowired
    private ResumeJobService resumeJobService;

    @Autowired
    private ResumeProjectService resumeProjectService;

    @Autowired
    private UsrFileService usrFileService;

    public ResumeMainService() {
    }

    @Autowired
    public ResumeMainService(ResumeMainDao resumeMainDao) {
        super(resumeMainDao);
        this.resumeMainDao = resumeMainDao;
    }

    public ResumeMainDto findDtoByUsrId(Integer usrId){
        ResumeMain resumeMain = resumeMainDao.findByUsrId(usrId);
        ResumeMainDto resumeMainDto =  ClassUtils.convert(ResumeMainDto.class,resumeMain);
        loadDto(resumeMainDto,resumeMain);
        return resumeMainDto;
    }

    private void loadDto(ResumeMainDto resumeMainDto, ResumeMain resumeMain){
        // 学历由数字转换为中文
        switch (resumeMain.getEducation()){
            case 0:
                resumeMainDto.setEducation("大专");
                break;
            case 1:
                resumeMainDto.setEducation("本科");
                break;
            case 2:
                resumeMainDto.setEducation("研究生");
                break;
            default:
                resumeMainDto.setEducation("");
                LOG.info("无法识别的学历："+resumeMain.getEducation());
        }

        Integer resumeId = resumeMain.getId();

        //工作经历
        resumeMainDto.setResumeJobDtoList(resumeJobService.findDtoByResumeId(resumeId));

        //项目经历
        resumeMainDto.setResumeProjectDtoList(resumeProjectService.findDtoByResumeId(resumeId));
    }
}
