package com.xiaohe66.web.code.security.dao;


import com.xiaohe66.web.base.base.BaseDao;
import com.xiaohe66.web.code.security.po.Func;

import java.util.List;
import java.util.Set;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
public interface FuncDao extends BaseDao<Func> {
    List<Func> findFuncByUsrId(Long usrId);
    Set<String> findFuncNameByUsrId(Long usrId);
    Set<String> findFuncNameInRoleIdSet(Set<String> roleIdSet);
}
