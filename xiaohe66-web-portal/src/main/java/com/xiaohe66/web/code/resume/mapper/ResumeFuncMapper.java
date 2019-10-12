package com.xiaohe66.web.code.resume.mapper;

import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.code.resume.po.ResumeFunc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xh
 * @date 18-10-12 011
 */
public interface ResumeFuncMapper extends IBaseMapper<ResumeFunc> {

    /**
     * 查询项目对应的功能点
     * @param projectId 项目id
     * @return List<ResumeFunc>
     */
    List<ResumeFunc> findByProjectId(@Param("projectId") Integer projectId);
}
