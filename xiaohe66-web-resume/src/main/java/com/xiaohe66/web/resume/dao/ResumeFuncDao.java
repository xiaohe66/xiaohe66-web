package com.xiaohe66.web.resume.dao;

import com.xiaohe66.web.base.base.BaseDao;
import com.xiaohe66.web.resume.po.ResumeFunc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xh
 * @date 18-10-12 011
 */
public interface ResumeFuncDao extends BaseDao<ResumeFunc>{

    /**
     * 查询项目对应的功能点
     * @param projectId 项目id
     * @return List<ResumeFunc>
     */
    List<ResumeFunc> findByProjectId(@Param("projectId")Long projectId);
}
