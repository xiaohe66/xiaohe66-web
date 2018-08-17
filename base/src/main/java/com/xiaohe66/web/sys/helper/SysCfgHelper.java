package com.xiaohe66.web.sys.helper;

import com.xiaohe66.web.common.data.ParamFinal;
import com.xiaohe66.web.common.util.Check;
import com.xiaohe66.web.common.util.StrUtils;
import com.xiaohe66.web.sys.po.SysCfg;
import com.xiaohe66.web.sys.service.SysCfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Component
public class SysCfgHelper {

    private static Map<String,String> cfgMap;

    @Autowired
    private SysCfgService cfgService;

    public synchronized void refresh(){
        List<SysCfg> cfgList = cfgService.findAll();
        HashMap<String,String> map = new HashMap<>(cfgList.size());
        for (SysCfg sysCfg : cfgList) {
            map.put(sysCfg.getCfgKey(),sysCfg.getCfgVal());
        }
        cfgMap = map;
    }

    public static String getValue(String key){
        Check.notEmptyCheck(key);
        return cfgMap.get(key);
    }

    public static Long findXhUsrId(){
        String usrIdStr = getValue(ParamFinal.CFG_KEY_XIAO_HE_USR_ID);
        return StrUtils.toLong(usrIdStr);
    }
}
