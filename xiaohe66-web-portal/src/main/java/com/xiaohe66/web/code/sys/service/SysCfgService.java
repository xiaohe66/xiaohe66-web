package com.xiaohe66.web.code.sys.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.code.sys.mapper.SysCfgMapper;
import com.xiaohe66.web.code.sys.po.SysCfg;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统配置项service
 *
 * @author xiaohe
 * @time 17-11-07 007
 */
@Service
public class SysCfgService extends AbstractService<SysCfgMapper,SysCfg>{

    public List<SysCfg> findAll(){
        return list();
    }
}
