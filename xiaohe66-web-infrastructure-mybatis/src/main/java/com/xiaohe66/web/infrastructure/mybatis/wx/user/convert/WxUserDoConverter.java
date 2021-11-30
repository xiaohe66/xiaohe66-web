package com.xiaohe66.web.infrastructure.mybatis.wx.user.convert;

import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.value.WxUnionId;
import com.xiaohe66.web.domain.wx.user.value.WxUserId;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.model.WxUserDo;
import com.xiaohe66.web.integration.DoConverter;
import com.xiaohe66.web.integration.value.MobileNo;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.10.29 17:28
 */
@Mapper(componentModel = "spring")
public interface WxUserDoConverter extends DoConverter<WxUser, WxUserDo> {

    default WxUserId newWxUserId(Long id) {
        return ifPresent(id, WxUserId::new);
    }

    default WxUnionId newUnionId(String unionId) {
        return ifPresent(unionId, WxUnionId::new);
    }

    default MobileNo newMobileNo(String unionId) {
        return ifPresent(unionId, MobileNo::new);
    }

}
