package com.xiaohe66.web.org.dao;

import com.xiaohe66.web.org.po.UsrFile;
import com.xiaohe66.web.common.base.BaseDao;
import org.apache.ibatis.annotations.Param;

/**
 * @author xh
 * @date 18-03-12 012
 */
public interface UsrFileDao extends BaseDao<UsrFile>{
    /**
     * 根据commonFileId 查询
     * @param commonFileId    commonFileId
     * @return  UsrFile
     */
    UsrFile findByCommonFileId(@Param("commonFileId") Long commonFileId);
}
