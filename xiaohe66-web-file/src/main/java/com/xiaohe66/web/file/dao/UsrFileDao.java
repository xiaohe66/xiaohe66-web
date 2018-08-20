package com.xiaohe66.web.file.dao;

import com.xiaohe66.web.base.base.BaseDao;
import com.xiaohe66.web.file.po.UsrFile;
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
