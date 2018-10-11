package com.xiaohe66.web.resume.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.resume.dao.ResumeJobDao;
import com.xiaohe66.web.resume.po.ResumeJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xh
 * @date 18-10-11 011
 */
@Service
public class ResumeJobService extends AbstractService<ResumeJob>{

    private ResumeJobDao resumeJobDao;

    public ResumeJobService() {
    }

    @Autowired
    public ResumeJobService(ResumeJobDao resumeJobDao) {
        super(resumeJobDao);
        this.resumeJobDao = resumeJobDao;
    }

    public List<ResumeJob> findByResumeId(Long resumeId){
        Check.notNullCheck(resumeId);
        return resumeJobDao.findByResumeId(resumeId);
    }
}
