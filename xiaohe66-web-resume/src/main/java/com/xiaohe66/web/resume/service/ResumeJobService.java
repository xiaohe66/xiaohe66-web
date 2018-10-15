package com.xiaohe66.web.resume.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.file.service.UsrFileService;
import com.xiaohe66.web.resume.dao.ResumeJobDao;
import com.xiaohe66.web.resume.dto.ResumeJobDto;
import com.xiaohe66.web.resume.po.ResumeJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xh
 * @date 18-10-11 011
 */
@Service
public class ResumeJobService extends AbstractService<ResumeJob>{

    private static final Logger LOG = LoggerFactory.getLogger(ResumeJobService.class);

    private ResumeJobDao resumeJobDao;

    @Autowired
    private UsrFileService usrFileService;

    public ResumeJobService() {
    }

    @Autowired
    public ResumeJobService(ResumeJobDao resumeJobDao) {
        super(resumeJobDao);
        this.resumeJobDao = resumeJobDao;
    }

    public List<ResumeJobDto> findDtoByResumeId(Long resumeId){
        Check.notNullCheck(resumeId);
        List<ResumeJob> resumeJobList = resumeJobDao.findByResumeId(resumeId);

        return ClassUtils.convertList(ResumeJobDto.class,resumeJobList,(dto, po)->{
            try {
                dto.setImgFileId(usrFileService.findById(po.getLogo()).getFileId());
            }catch (Exception e){
                LOG.error("取得commonFileId出现问题",e);
            }
        });
    }
}
