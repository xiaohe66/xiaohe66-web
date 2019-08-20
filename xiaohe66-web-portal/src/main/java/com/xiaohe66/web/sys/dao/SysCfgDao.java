package com.xiaohe66.web.sys.dao;

import com.xiaohe66.web.base.base.BaseDao;
import com.xiaohe66.web.sys.po.SysCfg;

/**
 * @author xiaohe
 * @time 17-11-07 007
 */
public interface SysCfgDao extends BaseDao<SysCfg>{
    /**
     * 通过key获取value
     * @param cfgKey cfgKey
     * @return cfgValue
     */
    String findValByKey(String cfgKey);
}
