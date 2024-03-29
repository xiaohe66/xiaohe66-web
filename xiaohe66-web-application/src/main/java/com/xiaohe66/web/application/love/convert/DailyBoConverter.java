package com.xiaohe66.web.application.love.convert;

import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.application.love.bo.DailySaveBo;
import com.xiaohe66.web.application.love.result.DailyResult;
import com.xiaohe66.web.domain.love.agg.Daily;
import com.xiaohe66.web.domain.love.service.LoverService;
import com.xiaohe66.web.domain.love.value.DailyId;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.infrastructure.domain.adapter.love.DailyConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.30 14:50
 */
@Mapper
public abstract class DailyBoConverter implements DailyConverter {

    @Autowired
    protected SecurityService securityService;

    @Autowired
    protected LoverService loverService;

    @Mapping(target = "id", expression = "java(genId())")
    @Mapping(target = "loverId", expression = "java(loverService.getCurrentLoverId())")
    @Mapping(target = "createId", expression = "java(securityService.getCurrentAccountId())")
    public abstract Daily toSaveAgg(DailySaveBo bo);

    @Mapping(target = "createTime", source = "id")
    public abstract DailyResult toResult(Daily daily);

    public abstract List<DailyResult> toResult(List<Daily> daily);

    protected DailyId genId() {
        return new DailyId(IdWorker.genId());
    }

}
