package com.xiaohe66.web.org.service;

import com.github.pagehelper.PageHelper;
import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.org.dao.UsrFileLogDao;
import com.xiaohe66.web.org.po.UsrFileLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author xh
 * @date 18-04-15 015
 */
@Service
public class UsrFileLogService extends AbstractService<UsrFileLog>{

    private UsrFileLogDao usrFileLogDao;

    public UsrFileLogService() {
    }

    @Autowired
    public UsrFileLogService(UsrFileLogDao usrFileLogDao) {
        super(usrFileLogDao);
        this.usrFileLogDao = usrFileLogDao;
    }

    /**
     * 月下载量统计（近30天）
     * todo:改为统计全部，并在UsrFileService中控制top5，并且过滤掉删除的文件
     * @param usrId 针对某个用户，若传入null，则为全部用户
     * @return  id:用户文件id;
     *          count:该文件的下载数量
     */
    public List<Map<String,Long>> countDownloadOfMonth(Long usrId){
        return usrFileLogDao.countDownloadOfMonth(usrId);
    }
}
