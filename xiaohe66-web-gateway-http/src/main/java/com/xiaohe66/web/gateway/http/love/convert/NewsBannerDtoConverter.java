package com.xiaohe66.web.gateway.http.love.convert;

import com.xiaohe66.web.application.love.bo.NewsBannerListBo;
import com.xiaohe66.web.application.love.bo.NewsBannerSaveBo;
import com.xiaohe66.web.gateway.http.love.dto.NewsBannerListDto;
import com.xiaohe66.web.gateway.http.love.dto.NewsBannerSaveDto;
import com.xiaohe66.web.integration.domain.DataConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.12.19 13:30
 */
@Mapper(componentModel = "spring")
public interface NewsBannerDtoConverter extends DataConverter {

    NewsBannerSaveBo toBo(NewsBannerSaveDto dto);

    NewsBannerListBo toBo(NewsBannerListDto dto);

}
