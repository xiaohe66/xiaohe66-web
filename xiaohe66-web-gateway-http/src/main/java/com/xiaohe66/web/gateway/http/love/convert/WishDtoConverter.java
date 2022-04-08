package com.xiaohe66.web.gateway.http.love.convert;

import com.xiaohe66.web.application.love.bo.WishListBo;
import com.xiaohe66.web.application.love.bo.WishSaveBo;
import com.xiaohe66.web.gateway.http.love.dto.WishListDto;
import com.xiaohe66.web.gateway.http.love.dto.WishSaveDto;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2022.03.29 18:24
 */
@Mapper
public interface WishDtoConverter {

    WishSaveBo toBo(WishSaveDto dto);

    WishListBo toBo(WishListDto dto);

}
