package com.xiaohe66.web.code.resume.mapper;

import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.code.resume.po.ResumeProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xh
 * @date 18-10-12 011
 */
public interface ResumeProjectMapper extends IBaseMapper<ResumeProject> {

    /**
     * 根据简历id查询
     * @param resumeId 简历id
     * @return List<ResumeProject>
     */
    List<ResumeProject> findByResumeId(@Param("resumeId") Integer resumeId);
}
