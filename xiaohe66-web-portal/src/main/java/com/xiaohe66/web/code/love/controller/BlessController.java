package com.xiaohe66.web.code.love.controller;

import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.code.love.dto.BlessDto;
import com.xiaohe66.web.code.love.po.Bless;
import com.xiaohe66.web.code.love.service.BlessService;

/**
 * @author xiaohe
 * @time 2019.10.22 11:15
 */
@XhController("/love/bless")
public class BlessController extends BaseController<BlessService, Bless, BlessDto> {

    @Override
    protected void checkSelect(Bless bless) {
        // 查询不需要检查权限
    }
    
}
