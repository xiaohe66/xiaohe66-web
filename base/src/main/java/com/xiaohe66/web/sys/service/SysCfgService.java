package com.xiaohe66.web.sys.service;

import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.StrUtils;
import com.xiaohe66.web.sys.dao.SysCfgDao;
import com.xiaohe66.web.sys.po.SysCfg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统配置项service
 * 第一次调用方法时进行初始化，把数据库中的配置全部加载到内存中
 *
 * 提供一个刷新的方法refresh()，该方法可以重新获取数据库中的配置并加载到内存中
 *
 * @author xiaohe
 * @time 17-11-07 007
 */
@Service
public class SysCfgService extends AbstractService<SysCfg>{
    private SysCfgDao sysCfgDao;

    /**
     * 是否已加载数据库中的配置
     */
    private boolean isInit;
    private List<SysCfg> cfgList;

    public SysCfgService(){

    }

    @Autowired
    public SysCfgService(SysCfgDao sysCfgDao){
        this.sysCfgDao = sysCfgDao;
    }

    public void refresh(){
        cfgList = sysCfgDao.findByParam(null);
    }

    public String findValByKey(String cfgKey){
        if(StrUtils.isOneEmpty(cfgKey)){
            throw new XhException(CodeEnum.PARAM_ERR,"cfgKey="+cfgKey);
        }
        if(!isInit){
            refresh();
            isInit = true;
        }
        for (SysCfg sysCfg : cfgList) {
            if (cfgKey.equals(sysCfg.getCfgKey())) {
                return sysCfg.getCfgVal();
            }
        }
        return null;
    }
}
