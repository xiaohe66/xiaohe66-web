package com.xiaohe66.web.code.file.mapper;

import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.code.file.po.CommonFile;
import org.apache.ibatis.annotations.Param;

/**
 * @author xh
 * @date 18-03-22 022
 */
public interface CommonFileMapper extends IBaseMapper<CommonFile> {

    /**
     * 根据md5查询文件
     * @param md5   文件的md5摘要
     * @return  对应md5的记录
     */
    CommonFile findByMd5(@Param("md5") String md5);
}
