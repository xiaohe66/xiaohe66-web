package com.xiaohe66.web.infrastructure.mybatis.wx.user.convert;

import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.infrastructure.domain.adapter.wx.WxUserConverter;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.model.WxUserDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.10.29 17:28
 */
@Mapper
public interface WxUserDoConverter extends DoConverter<WxUser, WxUserDo>, WxUserConverter {

}
