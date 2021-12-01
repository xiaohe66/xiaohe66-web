package com.xiaohe66.web.application.love.convert;

import com.xiaohe66.web.application.love.result.LoverInfoResult;
import com.xiaohe66.web.domain.love.agg.Lover;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.integration.domain.DataConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author xiaohe
 * @since 2021.11.29 15:26
 */
@Mapper(componentModel = "spring")
public interface LoverBoConverter extends DataConverter {

    @Mapping(source = "lover.id", target = "id")
    @Mapping(source = "lover.id", target = "createTime")
    LoverInfoResult toResult(Lover lover, WxUser loverWxUser);

}
