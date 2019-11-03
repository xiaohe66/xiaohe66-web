package com.xiaohe66.web.code.love.controller;

import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.code.love.dto.WishLogDto;
import com.xiaohe66.web.code.love.po.WishLog;
import com.xiaohe66.web.code.love.service.WishLogService;

/**
 * @author xiaohe
 * @time 2019.10.30 17:08
 */
@XhController("/love/wish/log")
public class WishLogController extends BaseController<WishLogService, WishLog, WishLogDto> {

    @Override
    protected void checkSelect(WishLog wishLog) {
        // 不检查
    }
}
