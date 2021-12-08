package com.xiaohe66.web.application.account.convert;

import com.xiaohe66.web.application.account.bo.WxUserUpdateBo;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.value.WxUserAvatarUrl;
import com.xiaohe66.web.domain.wx.user.value.WxUserCity;
import com.xiaohe66.web.domain.wx.user.value.WxUserCountry;
import com.xiaohe66.web.domain.wx.user.value.WxUserNickname;
import com.xiaohe66.web.domain.wx.user.value.WxUserProvince;
import com.xiaohe66.web.domain.wx.user.value.WxUserSex;
import com.xiaohe66.web.integration.domain.DataConverter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @author xiaohe
 * @since 2021.12.08 14:09
 */
@Mapper(componentModel = "spring")
public interface WxUserBoConverter extends DataConverter {

    void setUserInfo(@MappingTarget WxUser wxUser, WxUserUpdateBo bo);

    default WxUserNickname nweNickname(String str) {
        return ifPresent(str, WxUserNickname::new);
    }

    default WxUserAvatarUrl newAvatarUrl(String str) {
        return ifPresent(str, WxUserAvatarUrl::new);
    }

    default WxUserProvince newProvince(String str) {
        return ifPresent(str, WxUserProvince::new);
    }

    default WxUserCity newCity(String str) {
        return ifPresent(str, WxUserCity::new);
    }

    default WxUserCountry newCountry(String str) {
        return ifPresent(str, WxUserCountry::new);
    }

    default WxUserSex newSex(Integer sex) {
        return ifPresent(sex, WxUserSex::valueOf);
    }

}
