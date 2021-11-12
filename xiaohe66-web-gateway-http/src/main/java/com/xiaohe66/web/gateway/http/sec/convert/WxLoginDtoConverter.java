package com.xiaohe66.web.gateway.http.sec.convert;

import com.xiaohe66.web.application.sys.sec.bo.WxLoginBo;
import com.xiaohe66.web.gateway.http.DtoConverter;
import com.xiaohe66.web.gateway.http.sec.req.WxLoginRequest;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.11.12 17:14
 */
@Mapper(componentModel = "spring")
public interface WxLoginDtoConverter extends DtoConverter<WxLoginRequest, WxLoginBo> {

    default WxLoginBo.Type asType(String type) {

        if (type == null) {
            return null;
        }

        switch (type) {
            case "love":
                return WxLoginBo.Type.LOVE;

            case "todo":
                return WxLoginBo.Type.TODO;

            default:
                return null;
        }
    }
}
