package com.xiaohe66.web.code.sys.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.code.sys.helper.SysCfgHelper;
import com.xiaohe66.web.code.sys.mapper.SysCfgMapper;
import com.xiaohe66.web.code.sys.po.SysCfg;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统配置项service
 *
 * @author xiaohe
 * @time 17-11-07 007
 */
@Service
public class SysCfgService extends AbstractService<SysCfgMapper, SysCfg> implements InitializingBean {

    public List<SysCfg> findAll() {
        return list();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SysCfgHelper.refresh(findAll());
    }
}
