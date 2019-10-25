package com.xiaohe66.web.code.file.mapper;

import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.code.file.po.UserFile;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author xh
 * @date 18-03-12 012
 */
public interface UserFileMapper extends IBaseMapper<UserFile> {
    /**
     * 根据commonFileId 查询
     * @param commonFileId    commonFileId
     * @return  UsrFile
     */
    UserFile findByCommonFileId(@Param("commonFileId") Integer commonFileId);

    /**
     * 取得commonFileId
     * @param id id
     * @return commonFileId
     */
    @Select("select file_id from xiaohe66_web_org_usr_file where id = #{id}")
    Integer findCommonFileId(@Param("id") Integer id);
}
