package com.xiaohe66.web.file.dao;

import com.xiaohe66.web.base.base.BaseDao;
import com.xiaohe66.web.file.po.UsrFile;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 取得commonFileId
     * @param id id
     * @return commonFileId
     */
    @Select("select file_id from xiaohe66_web_org_usr_file where id = #{id}")
    Long findCommonFileId(@Param("id") Long id);
}
