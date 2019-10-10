package com.xiaohe66.web.code.file.dao;

import com.xiaohe66.web.base.base.BaseDao;
import com.xiaohe66.web.code.file.po.CommonFileTmp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author xh
 * @version 1.0
 * @time 2018-07-09 15:13
 */
public interface CommonFileTmpDao extends BaseDao<CommonFileTmp>{

    /**
     * 返回md5对应的已上传完的临时文件区块序号
     *
     * @param md5   文件的md5值
     * @return  已上传完的区块Set集合
     */
    Set<Integer> findFinishChunk(@Param("md5") String md5);

    /**
     * 返回所有md5的临时文件
     * @param md5   文件的md5值
     * @return  {@link List<CommonFileTmp>},md5对应的所有临时文件实例
     */
    List<CommonFileTmp> findFileTmp(@Param("md5") String md5);

    /**
     * 统计md5对应文件上传完的临时文件区块数量
     * @param md5   文件的md5值
     * @return  md5值对应文件上传完成的数量
     */
    int countFinish(@Param("md5") String md5);

    /**
     * 返回对应的区块是否上传完成
     * @param md5       文件的md5值
     * @param chunk     区块数
     * @return  该md5对应的区块上传完时返回true，反之返回false
     */
    boolean isExist(@Param("md5") String md5, @Param("chunk") int chunk);

}
