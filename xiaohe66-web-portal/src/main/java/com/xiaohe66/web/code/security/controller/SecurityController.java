package com.xiaohe66.web.code.security.controller;

import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.base.base.BaseDto;
import com.xiaohe66.web.base.base.BasePo;
import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.base.base.impl.AbstractService;

/**
 * @author xiaohe
 * @time 2019.10.21 10:58
 */
public class SecurityController<S extends AbstractService<? extends IBaseMapper, P>, P extends BasePo, D extends BaseDto>
        extends BaseController<S, P, D> {

    protected static final String SEC_ADMIN_ROLE_NAME = "secAdmin";

    @Override
    protected void checkSave(P po) {
        checkRole(SEC_ADMIN_ROLE_NAME);
        checkSavePermitted();
    }

    @Override
    protected void checkDelete(Integer id) {
        checkRole(SEC_ADMIN_ROLE_NAME);
        checkDeletePermitted();
    }

    @Override
    protected void checkUpdate(P po) {
        checkRole(SEC_ADMIN_ROLE_NAME);
        checkUpdatePermitted();
    }

    @Override
    protected void checkSelect(P po) {
        checkRole(SEC_ADMIN_ROLE_NAME);
        checkSelectPermitted();
    }
}
