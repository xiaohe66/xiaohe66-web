package com.xiaohe66.web.resume.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.file.service.UsrFileService;
import com.xiaohe66.web.resume.dao.ResumeProjectDao;
import com.xiaohe66.web.resume.dto.ResumeProjectDto;
import com.xiaohe66.web.resume.po.ResumeProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xh
 * @date 18-10-12 012
 */
@Service
public class ResumeProjectService extends AbstractService<ResumeProject>{

    private static final Logger LOG = LoggerFactory.getLogger(ResumeProjectService.class);

    private ResumeProjectDao resumeProjectDao;

    @Autowired
    private UsrFileService usrFileService;

    @Autowired
    private ResumeFuncService resumeFuncService;

    public ResumeProjectService() {
    }

    @Autowired
    public ResumeProjectService(ResumeProjectDao resumeProjectDao) {
        super(resumeProjectDao);
        this.resumeProjectDao = resumeProjectDao;
    }

    public List<ResumeProjectDto> findDtoByResumeId(Long resumeId){

        List<ResumeProject> resumeProjectList =  resumeProjectDao.findByResumeId(resumeId);
        return ClassUtils.convertList(ResumeProjectDto.class,resumeProjectList,(dto,po)->{
            try {
                dto.setImgFileId(usrFileService.findCommonFileId(po.getLogo()));
            }catch (Exception e){
                LOG.error("取commonFileId出现问题",e);
            }
            dto.setResumeFuncDtoList(resumeFuncService.findDtoByProjectId(po.getId()));
        });
    }
}
