package com.xiaohe66.web.code.sys.helper;

import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.StrUtils;
import com.xiaohe66.web.code.sys.po.SysCfg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统配置
 * <p>在系统初始化时，必须先把调用一次refresh()方法
 * 该方法会把数据库中的配置全部加载到内存中。
 * 此外，后续更改了系统配置的值，也可以调用refresh()方法进行重新加载
 *
 * @author xh
 * @version 1.0
 * @time 2018-08-16 15:59
 */
public class SysCfgHelper {

    private SysCfgHelper() {
    }

    private static Map<String, Object> cfgMap;

    public static void refresh(List<SysCfg> cfgList) {
        Check.notEmpty(cfgList);
        HashMap<String, Object> map = new HashMap<>();
        for (SysCfg sysCfg : cfgList) {
            map.put(sysCfg.getCfgKey(), sysCfg.getCfgVal());
        }
        cfgMap = map;
    }

    public static Object getObj(String key) {
        Check.notEmpty(key);
        return cfgMap.get(key);
    }

    public static String getString(String key) {
        Object obj = getObj(key);
        return obj == null ? "" : obj.toString();
    }
}
