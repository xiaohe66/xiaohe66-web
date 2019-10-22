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
public class SecurityController<S extends AbstractService<? extends IBaseMapper, T>, T extends BasePo, D extends BaseDto>
        extends BaseController<S, T, D> {

    protected static final String ADMIN_ROLE_NAME = "admin";

    // todo : 开发阶段，暂时只限制角色，不限制功能

    @Override
    protected void checkSave(T po) {
        checkRole(ADMIN_ROLE_NAME);
//        super.checkSave();
    }

    @Override
    protected void checkDelete(Integer id) {
        checkRole(ADMIN_ROLE_NAME);
//        super.checkDelete();
    }

    @Override
    protected void checkUpdate(T po) {
        checkRole(ADMIN_ROLE_NAME);
//        super.checkUpdate();
    }

    @Override
    protected void checkSelect() {
        checkRole(ADMIN_ROLE_NAME);
//        super.checkSelect();
    }
}
