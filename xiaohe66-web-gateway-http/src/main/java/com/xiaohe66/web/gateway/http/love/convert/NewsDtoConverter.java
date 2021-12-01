package com.xiaohe66.web.gateway.http.love.convert;

import com.xiaohe66.web.application.love.bo.NewsListBo;
import com.xiaohe66.web.application.love.bo.NewsSaveBo;
import com.xiaohe66.web.gateway.http.DtoConverter;
import com.xiaohe66.web.gateway.http.love.dto.NewsListDto;
import com.xiaohe66.web.gateway.http.love.dto.NewsSaveDto;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.12.01 17:55
 */
@Mapper(componentModel = "spring")
public interface NewsDtoConverter extends DtoConverter {

    NewsSaveBo toBo(NewsSaveDto dto);

    NewsListBo toBo(NewsListDto dto);

}
