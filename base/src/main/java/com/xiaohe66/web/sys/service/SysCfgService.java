package com.xiaohe66.web.sys.service;

import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.StrUtils;
import com.xiaohe66.web.sys.dao.SysCfgDao;
import com.xiaohe66.web.sys.po.SysCfg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaohe
 * @time 17-11-07 007
 */
@Service
public class SysCfgService extends AbstractService<SysCfg>{
    private SysCfgDao sysCfgDao;

    public SysCfgService(){

    }

    @Autowired
    public SysCfgService(SysCfgDao sysCfgDao){
        this.sysCfgDao = sysCfgDao;
    }

    public String searchValByName(String cfgKey){
        if(StrUtils.isOneEmpty(cfgKey)){
            throw new XhException(CodeEnum.PARAM_ERR,"cfgKey="+cfgKey);
        }
        return sysCfgDao.searchValByKey(cfgKey);
    }
}
