package com.xiaohe66.web.code.resume.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.file.service.UserFileService;
import com.xiaohe66.web.code.resume.mapper.ResumeProjectMapper;
import com.xiaohe66.web.code.resume.dto.ResumeProjectDto;
import com.xiaohe66.web.code.resume.po.ResumeProject;
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
public class ResumeProjectService extends AbstractService<ResumeProjectMapper,ResumeProject>{

    private static final Logger LOG = LoggerFactory.getLogger(ResumeProjectService.class);

    @Autowired
    private UserFileService usrFileService;

    @Autowired
    private ResumeFuncService resumeFuncService;

    public List<ResumeProjectDto> findDtoByResumeId(Integer resumeId){

        List<ResumeProject> resumeProjectList =  baseMapper.findByResumeId(resumeId);
        return ClassUtils.convert(ResumeProjectDto.class,resumeProjectList,(dto, po)->{
            try {
                dto.setImgFileId(usrFileService.findCommonFileId(po.getLogo()));
            }catch (Exception e){
                LOG.error("取commonFileId出现问题",e);
            }
            dto.setResumeFuncDtoList(resumeFuncService.findDtoByProjectId(po.getId()));
        });
    }
}
