package com.xiaohe66.web.sys.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.sys.dao.SysCfgDao;
import com.xiaohe66.web.sys.po.SysCfg;
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
public class SysCfgService extends AbstractService<SysCfg>{

    public SysCfgService(){

    }

    @Autowired
    public SysCfgService(SysCfgDao sysCfgDao){
        super(sysCfgDao);
    }

    public List<SysCfg> findAll(){
        return findByParam(null);
    }
}
