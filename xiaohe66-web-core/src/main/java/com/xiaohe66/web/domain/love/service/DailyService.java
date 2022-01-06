package com.xiaohe66.web.domain.love.service;

import com.xiaohe66.common.util.Assert;
import com.xiaohe66.common.util.ex.ErrorCodeEnum;
import com.xiaohe66.web.domain.love.agg.Daily;
import com.xiaohe66.web.domain.love.repository.DailyRepository;
import com.xiaohe66.web.domain.love.value.DailyId;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xiaohe
 * @since 2021.11.30 12:23
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DailyService {

    private final DailyRepository dailyRepository;

    private final LoverService loverService;
    private final SecurityService securityService;

    public void save(Daily daily) {

        dailyRepository.save(daily);
    }

    public void removeById(DailyId id) {

        Daily daily = dailyRepository.getById(id);

        Assert.notNull(daily, ErrorCodeEnum.NOT_FOUND_DATA);

        securityService.checkCreatorPermission(daily.getCreateId());

        dailyRepository.removeById(id);
    }

    public Daily getById(DailyId id) {

        Daily daily = dailyRepository.getById(id);

        Assert.notNull(daily, ErrorCodeEnum.NOT_FOUND_DATA);

        // 检查权限，是否为当前情侣
        loverService.checkLoverPermission(daily.getLoverId());

        return daily;
    }

}
