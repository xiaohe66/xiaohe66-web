package com.xiaohe66.web.code.resume.dao;

import com.xiaohe66.web.base.base.BaseDao;
import com.xiaohe66.web.code.resume.po.ResumeMain;
import org.apache.ibatis.annotations.Param;

/**
 * @author xh
 * @date 18-10-09 009
 */
public interface ResumeMainDao extends BaseDao<ResumeMain>{

    /**
     * 创建者id查询
     * @param usrId usrId
     * @return ResumeMain
     */
    ResumeMain findByUsrId(@Param("usrId") Long usrId);
}
