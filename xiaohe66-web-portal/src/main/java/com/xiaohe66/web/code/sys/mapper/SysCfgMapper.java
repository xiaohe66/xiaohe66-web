package com.xiaohe66.web.code.sys.mapper;

import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.code.sys.po.SysCfg;

/**
 * @author xiaohe
 * @time 17-11-07 007
 */
public interface SysCfgMapper extends IBaseMapper<SysCfg> {
    /**
     * 通过key获取value
     * @param cfgKey cfgKey
     * @return cfgValue
     */
    String findValByKey(String cfgKey);
}
