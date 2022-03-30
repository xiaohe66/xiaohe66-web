package com.xiaohe66.web.infrastructure.mybatis.love.convert;

import com.xiaohe66.web.domain.love.agg.Daily;
import com.xiaohe66.web.infrastructure.domain.adapter.love.DailyConverter;
import com.xiaohe66.web.infrastructure.mybatis.love.model.DailyDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.11.30 12:13
 */
@Mapper
public interface DailyDoConverter extends DoConverter<Daily, DailyDo>, DailyConverter {

}
