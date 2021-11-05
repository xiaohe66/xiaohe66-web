package com.xiaohe66.web.application.convert;

import com.xiaohe66.web.application.request.WxLoginRequest;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @author xiaohe
 * @since 2021.10.28 16:40
 */
@Mapper(componentModel = "spring")
public interface WxLoginDataConverter {

    void copyValueToWxUser(@MappingTarget WxUser wxUser, WxLoginRequest loginRequest);

}
