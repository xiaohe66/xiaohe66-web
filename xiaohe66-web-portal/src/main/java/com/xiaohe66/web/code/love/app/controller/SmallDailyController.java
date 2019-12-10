package com.xiaohe66.web.code.love.app.controller;

import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.base.exception.param.IllegalParamException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.code.love.app.dto.SmallDailyDto;
import com.xiaohe66.web.code.love.app.po.SmallDaily;
import com.xiaohe66.web.code.love.app.service.SmallDailyService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaohe
 * @time 2019.12.06 16:14
 */
@XhController("/love/app/smallDaily")
@Slf4j
public class SmallDailyController extends BaseController<SmallDailyService, SmallDaily, SmallDailyDto> {

    @Override
    protected void checkSave(SmallDaily po) {
        super.checkSave(po);
        checkPo(po);
    }

    @Override
    protected void checkUpdate(SmallDaily po) {
        super.checkUpdate(po);
        checkPo(po);
    }

    private void checkPo(SmallDaily po) {
        Check.notEmpty(po.getDesc());

        Integer mood = po.getMood();
        Check.notEmpty(mood);

        if (mood < SmallDaily.Mood.MIN.getVal() ||
                mood > SmallDaily.Mood.MAX.getVal()) {
            throw new IllegalParamException("mood参数取值错误");
        }

    }
}
