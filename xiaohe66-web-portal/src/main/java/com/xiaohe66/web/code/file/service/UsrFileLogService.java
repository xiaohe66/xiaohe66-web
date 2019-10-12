package com.xiaohe66.web.code.file.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.util.CollectionUtils;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.code.file.mapper.UsrFileLogMapper;
import com.xiaohe66.web.code.file.po.UsrFileDownloadCount;
import com.xiaohe66.web.code.file.po.UsrFileLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xh
 * @date 18-04-15 015
 */
@Service
public class UsrFileLogService extends AbstractService<UsrFileLogMapper,UsrFileLog>{

    private static final Logger LOG = LoggerFactory.getLogger(UsrFileLogService.class);

    /**
     * 每个会话只能增加1的下载量
     * @param po 插入的实体
     * @param currentUsrId 当前操作者id
     */
    @Override
    public void add(UsrFileLog po, Integer currentUsrId) {
        String ip = WebUtils.getRequestIP();
        LOG.debug("ip："+ip);

        Integer usrFileId = po.getUsrFileId();

        Set<Integer> usrFileIdSet = WebUtils.getSessionAttr(Final.Str.USR_FILE_LOG_CACHE);

        if(CollectionUtils.isNull(usrFileIdSet)){
            usrFileIdSet = new HashSet<>(4);
            WebUtils.setSessionAttr(Final.Str.USR_FILE_LOG_CACHE,usrFileIdSet);
        }

        if (!usrFileIdSet.contains(usrFileId)) {

            po.setIp(ip);
            po.setCreateTime(new Date());
            po.setCreateId(currentUsrId);
            super.add(po,currentUsrId);

            usrFileIdSet.add(usrFileId);
        }

    }

    /**
     * 月下载量统计（近30天）
     * @param usrId 针对某个用户，若传入null，则为全部用户
     * @return  id:用户文件id;
     *          count:该文件的下载数量
     */
    public List<UsrFileDownloadCount> countDownloadOfMonth(Integer usrId){
        return baseMapper.countDownloadOfMonth(usrId);
    }
}
