package com.xiaohe66.web.gateway.http.account.convert;

import com.xiaohe66.web.application.account.bo.WxUserUpdateBo;
import com.xiaohe66.web.gateway.http.DtoConverter;
import com.xiaohe66.web.gateway.http.account.dto.WxUserUpdateDto;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.12.07 13:55
 */
@Mapper(componentModel = "spring")
public interface WxUserDtoConverter extends DtoConverter {

    WxUserUpdateBo toBo(WxUserUpdateDto dto);
}
