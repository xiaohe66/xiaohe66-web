package com.xiaohe66.web.code.resume.mapper;

import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.code.resume.po.ResumeMain;
import org.apache.ibatis.annotations.Param;

/**
 * @author xh
 * @date 18-10-09 009
 */
public interface ResumeMainMapper extends IBaseMapper<ResumeMain> {

    /**
     * 创建者id查询
     * @param usrId usrId
     * @return ResumeMain
     */
    ResumeMain findByUsrId(@Param("usrId") Integer usrId);
}
