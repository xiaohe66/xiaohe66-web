package com.xiaohe66.web.infrastructure.domain.adapter.wx;

import com.xiaohe66.web.domain.wx.user.value.WxUnionId;
import com.xiaohe66.web.domain.wx.user.value.WxUserAvatarUrl;
import com.xiaohe66.web.domain.wx.user.value.WxUserCity;
import com.xiaohe66.web.domain.wx.user.value.WxUserCountry;
import com.xiaohe66.web.domain.wx.user.value.WxUserId;
import com.xiaohe66.web.domain.wx.user.value.WxUserNickname;
import com.xiaohe66.web.domain.wx.user.value.WxUserProvince;
import com.xiaohe66.web.domain.wx.user.value.WxUserSessionKey;
import com.xiaohe66.web.domain.wx.user.value.WxUserSex;
import com.xiaohe66.web.infrastructure.domain.adapter.DataConverter;
import com.xiaohe66.web.integration.value.MobileNo;

/**
 * @author xiaohe
 * @since 2021.10.29 17:28
 */
public interface WxUserConverter extends DataConverter {

    default WxUserId newWxUserId(Long id) {
        return ifPresent(id, WxUserId::new);
    }

    default WxUnionId newUnionId(String unionId) {
        return ifPresent(unionId, WxUnionId::new);
    }

    default MobileNo newMobileNo(String unionId) {
        return ifPresent(unionId, MobileNo::new);
    }

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

    default WxUserSessionKey newSessionKey(String str) {
        return ifPresent(str, WxUserSessionKey::new);
    }

}
