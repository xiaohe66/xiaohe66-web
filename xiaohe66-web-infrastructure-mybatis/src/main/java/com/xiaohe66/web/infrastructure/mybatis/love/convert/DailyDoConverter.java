package com.xiaohe66.web.infrastructure.mybatis.love.convert;

import com.xiaohe66.web.domain.love.agg.Daily;
import com.xiaohe66.web.domain.love.value.DailyDesc;
import com.xiaohe66.web.domain.love.value.DailyId;
import com.xiaohe66.web.domain.love.value.DailyMood;
import com.xiaohe66.web.infrastructure.mybatis.love.model.DailyDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.11.30 12:13
 */
@Mapper(componentModel = "spring")
public interface DailyDoConverter extends DoConverter<Daily, DailyDo> {

    default DailyId newDailyId(Long id) {
        return ifPresent(id, DailyId::new);
    }

    default DailyDesc newDailyDesc(String desc) {
        return ifPresent(desc, DailyDesc::new);
    }

    default DailyMood newDailyMod(Integer mood) {
        return ifPresent(mood, DailyMood::valueOf);
    }

}
