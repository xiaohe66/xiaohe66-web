package com.xiaohe66.web.resume.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.file.service.UsrFileService;
import com.xiaohe66.web.resume.dao.ResumeMainDao;
import com.xiaohe66.web.resume.dto.ResumeJobDto;
import com.xiaohe66.web.resume.dto.ResumeMainDto;
import com.xiaohe66.web.resume.po.ResumeJob;
import com.xiaohe66.web.resume.po.ResumeMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private UsrFileService usrFileService;

    public ResumeMainService() {
    }

    @Autowired
    public ResumeMainService(ResumeMainDao resumeMainDao) {
        super(resumeMainDao);
        this.resumeMainDao = resumeMainDao;
    }

    public ResumeMainDto findDtoByUsrId(Long usrId){
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

        //工作经历
        List<ResumeJob> resumeJobList = resumeJobService.findByResumeId(resumeMain.getId());
        resumeMainDto.setResumeJobDtoList(ClassUtils.convertList(ResumeJobDto.class,resumeJobList,(dto,po)->{
            try {
                dto.setImgFileId(usrFileService.findById(po.getLogo()).getFileId());
            }catch (Exception e){
                LOG.error("取得commonFileId出现问题",e);
            }
        }));
    }
}
