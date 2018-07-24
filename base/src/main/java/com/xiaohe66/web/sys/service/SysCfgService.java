package com.xiaohe66.web.sys.service;

import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.StrEnum;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.StrUtils;
import com.xiaohe66.web.sys.dao.SysCfgDao;
import com.xiaohe66.web.sys.po.SysCfg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统配置项service
 * <p>在系统初始化时，必须先把调用一次refresh()方法
 * 该方法会把数据库中的配置全部加载到内存中。
 * 此外，后续更改了系统配置的值，也可以调用refresh()方法进行重新加载
 *
 * @author xiaohe
 * @time 17-11-07 007
 */
@Service
public class SysCfgService extends AbstractService<SysCfg>{
    private SysCfgDao sysCfgDao;

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
        for (SysCfg sysCfg : cfgList) {
            if (cfgKey.equals(sysCfg.getCfgKey())) {
                return sysCfg.getCfgVal();
            }
        }
        return null;
    }

    public Long findXhUsrId(){
        String usrIdStr = findValByKey(StrEnum.CFG_KEY_XIAO_HE_USR_ID.data());
        return StrUtils.toLong(usrIdStr);
    }
}
