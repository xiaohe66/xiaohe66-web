package com.xiaohe66.web.code.file.mapper;

import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.code.file.po.UsrFileDownloadCount;
import com.xiaohe66.web.code.file.po.UsrFileLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xh
 * @date 18-04-15 015
 */
public interface UsrFileLogMapper extends IBaseMapper<UsrFileLog> {

    /**
     * 统计某个文件的下载数量，按下载量倒序排序
     *
     * @param usrId 针对某个用户，若传入null，则为全部用户
     * @return id:用户文件id;
     * count:该文件的下载数量
     */
    List<UsrFileDownloadCount> countDownloadOfMonth(@Param("usrId") Integer usrId);

}
