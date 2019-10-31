package com.xiaohe66.web.code.file.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.code.file.mapper.UsrFileLogMapper;
import com.xiaohe66.web.code.file.po.UsrFileDownloadCount;
import com.xiaohe66.web.code.file.po.UsrFileLog;
import com.xiaohe66.web.code.org.helper.UserHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xh
 * @date 18-04-15 015
 */
@Service
public class UsrFileLogService extends AbstractService<UsrFileLogMapper, UsrFileLog> {

    private static final Logger logger = LoggerFactory.getLogger(UsrFileLogService.class);

    /**
     * 每个会话只能增加1的下载量
     *
     * @param po 插入的实体
     */
    @Override
    public boolean save(UsrFileLog po) {
        String ip = WebUtils.getRequestIP();
        logger.debug("ip：{}", ip);

        Integer usrFileId = po.getUsrFileId();

        Set<Integer> usrFileIdSet = WebUtils.getSessionAttr(Final.Str.USR_FILE_LOG_CACHE);

        if (usrFileIdSet == null) {
            usrFileIdSet = new HashSet<>();
            WebUtils.setSessionAttr(Final.Str.USR_FILE_LOG_CACHE, usrFileIdSet);
        }

        if (!usrFileIdSet.contains(usrFileId)) {
            usrFileIdSet.add(usrFileId);
            po.setIp(ip);
            po.setCreateId(UserHelper.getCurrentUsrIdNotEx());
            return super.save(po);
        }
        return false;
    }

    /**
     * 月下载量统计（近30天）
     *
     * @param usrId 针对某个用户，若传入null，则为全部用户
     * @return id:用户文件id;
     * count:该文件的下载数量
     */
    public List<UsrFileDownloadCount> countDownloadOfMonth(Integer usrId) {
        return baseMapper.countDownloadOfMonth(usrId);
    }
}
