package com.xiaohe66.web.code.resume.mapper;

import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.code.resume.po.ResumeJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xh
 * @date 18-10-11 011
 */
public interface ResumeJobMapper extends IBaseMapper<ResumeJob> {

    /**
     * 查询resumeId 关联的工作经历
     * @param resumeId 简历id
     * @return List<ResumeJob>
     */
    List<ResumeJob> findByResumeId(@Param("resumeId") Integer resumeId);
}
