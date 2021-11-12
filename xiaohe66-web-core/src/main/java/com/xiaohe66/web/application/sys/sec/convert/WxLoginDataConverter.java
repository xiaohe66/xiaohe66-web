package com.xiaohe66.web.application.sys.sec.convert;

import com.xiaohe66.web.application.sys.sec.request.WxLoginRequest;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.value.WxTodoUserOpenId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @author xiaohe
 * @since 2021.10.28 16:40
 */
@Mapper(componentModel = "spring")
public interface WxLoginDataConverter {

    void copyValueToWxUser(@MappingTarget WxUser wxUser, WxLoginRequest loginRequest);

    default void setOpenId(WxUser wxUser, WxLoginRequest.Type type, String openId) {

        switch (type) {
            case TODO:
                wxUser.setWxTodoUserOpenId(new WxTodoUserOpenId(openId));
                break;

            case LOVE:
                break;

            default:
        }

    }
}
