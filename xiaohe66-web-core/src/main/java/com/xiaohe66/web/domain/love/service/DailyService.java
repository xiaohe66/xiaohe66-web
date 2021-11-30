package com.xiaohe66.web.domain.love.service;

import com.xiaohe66.web.domain.love.agg.Daily;
import com.xiaohe66.web.domain.love.repository.DailyRepository;
import com.xiaohe66.web.domain.love.value.DailyId;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
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

        if(daily == null){
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND_DATE);
        }

        if (!securityService.hasCreatorPermission(daily.getCreateId())) {
            throw new BusinessException(ErrorCodeEnum.NOT_DATA_PERMISSION);
        }

        dailyRepository.removeById(id);
    }

    public Daily getById(DailyId id) {

        Daily daily = dailyRepository.getById(id);

        // 检查权限，是否为当前情侣
        loverService.checkLoverPermission(daily.getLoverId());

        return daily;
    }

}
