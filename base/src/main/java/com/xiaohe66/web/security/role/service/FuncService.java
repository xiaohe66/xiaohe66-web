package com.xiaohe66.web.security.role.service;

import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.security.role.dao.FuncDao;
import com.xiaohe66.web.security.role.po.Func;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@Service
public class FuncService extends AbstractService<Func> {

    private FuncDao funcDao;

    public FuncService() {
    }

    @Autowired
    public FuncService(FuncDao funcDao) {
        super(funcDao);
        this.funcDao = funcDao;
    }

    public Set<String> findFuncNameByUsrId(Long usrId){
        return funcDao.findFuncNameByUsrId(usrId);
    }
}
