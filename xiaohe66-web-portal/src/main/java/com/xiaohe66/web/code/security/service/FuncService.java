package com.xiaohe66.web.code.security.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.code.security.dao.FuncDao;
import com.xiaohe66.web.code.security.po.Func;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@Service
public class FuncService extends AbstractService<FuncDao,Func> {

    public Set<String> findFuncNameByUsrId(Long usrId){
        return baseMapper.findFuncNameByUsrId(usrId);
    }
}
