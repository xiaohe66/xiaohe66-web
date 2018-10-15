package com.xiaohe66.web.resume.dao;

import com.xiaohe66.web.base.base.BaseDao;
import com.xiaohe66.web.resume.po.ResumeProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xh
 * @date 18-10-12 011
 */
public interface ResumeProjectDao extends BaseDao<ResumeProject>{

    /**
     * 根据简历id查询
     * @param resumeId 简历id
     * @return List<ResumeProject>
     */
    List<ResumeProject> findByResumeId(@Param("resumeId")Long resumeId);
}
