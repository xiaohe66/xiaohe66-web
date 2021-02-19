package com.xiaohe66.web.code.love.controller;

import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.base.base.BaseDto;
import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.code.love.po.LovePo;

/**
 * @author xiaohe
 * @time 2021.02.19 11:06
 */
public class LoveController<S extends AbstractService<? extends IBaseMapper, P>, P
        extends LovePo, D extends BaseDto> extends BaseController<S, P, D> {

    @Override
    public Result put(P po) {
        po.setLoverId(null);
        return super.put(po);
    }

}
