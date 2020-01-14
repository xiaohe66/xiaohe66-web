package com.xiaohe66.web.code.love.controller;

import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.exception.param.IllegalParamException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.code.love.dto.DailyDto;
import com.xiaohe66.web.code.love.po.Daily;
import com.xiaohe66.web.code.love.service.DailyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaohe
 * @time 2019.12.06 16:14
 */
@XhController("/love/app/smallDaily")
@Slf4j
@AllArgsConstructor
public class DailyController extends BaseController<DailyService, Daily, DailyDto> {

    @Override
    public Result put(Daily po) {
        po.setLoverId(null);
        return super.put(po);
    }

    @Override
    protected void checkSave(Daily po) {
        super.checkSave(po);
        checkPo(po);
    }

    @Override
    protected void checkUpdate(Daily po) {
        super.checkUpdate(po);
        checkPo(po);
    }

    private void checkPo(Daily po) {
        Check.notEmpty(po.getDesc());

        Integer mood = po.getMood();
        Check.notEmpty(mood);

        Daily.Mood[] moods = Daily.Mood.values();
        for (Daily.Mood sysMood : moods) {
            if (sysMood.getVal().equals(mood)) {
                return;
            }
        }

        throw new IllegalParamException("mood参数取值错误 : " + mood);
    }
}
