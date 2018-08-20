package com.xiaohe66.web.file.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.ParamFinal;
import com.xiaohe66.web.base.util.CollectionUtils;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.file.dao.UsrFileLogDao;
import com.xiaohe66.web.file.po.UsrFileLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xh
 * @date 18-04-15 015
 */
@Service
public class UsrFileLogService extends AbstractService<UsrFileLog>{

    private static final Logger LOG = LoggerFactory.getLogger(UsrFileLogService.class);

    private UsrFileLogDao usrFileLogDao;

    public UsrFileLogService() {
    }

    @Autowired
    public UsrFileLogService(UsrFileLogDao usrFileLogDao) {
        super(usrFileLogDao);
        this.usrFileLogDao = usrFileLogDao;
    }

    /**
     * 每个会话只能增加1的下载量
     * @param po 插入的实体
     * @param currentUsrId 当前操作者id
     */
    @Override
    public void add(UsrFileLog po, Long currentUsrId) {
        String ip = WebUtils.getRequestIP();
        LOG.debug("ip："+ip);

        Long usrFileId = po.getUsrFileId();

        Set<Long> usrFileIdSet = WebUtils.getSessionAttr(ParamFinal.USR_FILE_LOG_CACHE);

        if(CollectionUtils.isNull(usrFileIdSet)){
            usrFileIdSet = new HashSet<>(4);
            WebUtils.setSessionAttr(ParamFinal.USR_FILE_LOG_CACHE,usrFileIdSet);
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
    public List<Map<String,Long>> countDownloadOfMonth(Long usrId){
        return usrFileLogDao.countDownloadOfMonth(usrId);
    }
}
