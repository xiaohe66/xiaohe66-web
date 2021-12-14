package com.xiaohe66.web.application.love;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.application.aop.annotation.NeedLogin;
import com.xiaohe66.web.application.aop.annotation.NeedRoles;
import com.xiaohe66.web.application.love.bo.DailyListBo;
import com.xiaohe66.web.application.love.bo.DailySaveBo;
import com.xiaohe66.web.application.love.convert.DailyBoConverter;
import com.xiaohe66.web.application.love.result.DailyResult;
import com.xiaohe66.web.domain.love.agg.Daily;
import com.xiaohe66.web.domain.love.repository.DailyRepository;
import com.xiaohe66.web.domain.love.service.DailyService;
import com.xiaohe66.web.domain.love.service.LoverService;
import com.xiaohe66.web.domain.love.value.DailyDesc;
import com.xiaohe66.web.domain.love.value.DailyId;
import com.xiaohe66.web.domain.love.value.DailyMood;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.sys.sec.value.RoleName;
import com.xiaohe66.web.integration.domain.Paging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.30 14:04
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DailyAppService {

    private final DailyBoConverter boConverter;
    private final DailyRepository dailyRepository;
    private final SecurityService securityService;
    private final DailyService dailyService;
    private final LoverService loverService;

    @NeedRoles(RoleName.LOVER_ROLE_VALUE)
    public R<DailyResult> save(DailySaveBo bo) {

        LoverId loverId = loverService.getCurrentLoverId();
        Long id = IdWorker.genId();

        Daily daily = Daily.builder()
                .id(new DailyId(id))
                .createId(securityService.getCurrentAccountId())
                .loverId(loverId)
                .desc(new DailyDesc(bo.getDesc()))
                .mood(DailyMood.valueOf(bo.getMood()))
                .build();

        dailyService.save(daily);

        DailyResult result = boConverter.toResult(daily);

        return R.ok(result);
    }

    @NeedRoles(RoleName.LOVER_ROLE_VALUE)
    public R<Void> removeById(Long idValue) {

        DailyId id = new DailyId(idValue);

        dailyService.removeById(id);

        return R.ok();
    }

    @NeedLogin
    public R<List<DailyResult>> listByLoverId(DailyListBo bo) {

        LoverId loverId = loverService.getCurrentLoverId();

        Paging paging = new Paging(bo.getBefore(), bo.getSize());

        List<Daily> dailyList = dailyRepository.listByLoverIdDescId(loverId, paging);

        List<DailyResult> list = boConverter.toResult(dailyList);

        return R.ok(list);
    }

}
