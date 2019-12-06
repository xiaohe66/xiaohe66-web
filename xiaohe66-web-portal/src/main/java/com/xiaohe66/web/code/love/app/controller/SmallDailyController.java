package com.xiaohe66.web.code.love.app.controller;

import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.code.love.app.dto.SmallDailyDto;
import com.xiaohe66.web.code.love.app.po.SmallDaily;
import com.xiaohe66.web.code.love.app.service.SmallDailyService;

/**
 * @author xiaohe
 * @time 2019.12.06 16:14
 */
@XhController("/love/app/smallDaily")
public class SmallDailyController extends BaseController<SmallDailyService, SmallDaily, SmallDailyDto> {
}
