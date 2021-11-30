package com.xiaohe66.web.gateway.http.love.convert;

import com.xiaohe66.web.application.love.bo.DailyListBo;
import com.xiaohe66.web.application.love.bo.DailySaveBo;
import com.xiaohe66.web.gateway.http.DtoConverter;
import com.xiaohe66.web.gateway.http.love.dto.DailyListDto;
import com.xiaohe66.web.gateway.http.love.dto.DailySaveDto;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.11.30 14:28
 */
@Mapper(componentModel = "spring")
public interface DailyDtoConverter extends DtoConverter {

    DailySaveBo toBo(DailySaveDto dto);

    DailyListBo toBo(DailyListDto dto);

}
