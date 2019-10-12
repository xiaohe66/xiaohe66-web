package com.xiaohe66.web.code.sys.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.code.sys.dao.SysCfgDao;
import com.xiaohe66.web.code.sys.po.SysCfg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统配置项service
 *
 * @author xiaohe
 * @time 17-11-07 007
 */
@Service
public class SysCfgService extends AbstractService<SysCfgDao,SysCfg>{

    public List<SysCfg> findAll(){
        return list();
    }
}
