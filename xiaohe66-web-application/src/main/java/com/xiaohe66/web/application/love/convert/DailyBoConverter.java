package com.xiaohe66.web.application.love.convert;

import com.xiaohe66.web.application.love.result.DailyResult;
import com.xiaohe66.web.domain.love.agg.Daily;
import com.xiaohe66.web.integration.domain.DataConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.30 14:50
 */
@Mapper(componentModel = "spring")
public interface DailyBoConverter extends DataConverter {

    @Mapping(target = "createTime", source = "id")
    DailyResult toResult(Daily daily);

    List<DailyResult> toResult(List<Daily> daily);

}
