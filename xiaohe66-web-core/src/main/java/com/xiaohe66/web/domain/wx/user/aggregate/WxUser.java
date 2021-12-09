package com.xiaohe66.web.domain.wx.user.aggregate;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.wx.user.value.*;
import com.xiaohe66.web.integration.domain.Aggregate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * @author xiaohe
 * @since 2021.08.11 17:48
 */
@Builder
@Getter
@Setter
@ToString(exclude = "avatar")
@AllArgsConstructor
public class WxUser implements Aggregate<WxUser, WxUserId> {

    @NonNull
    private final WxUserId id;

    @NonNull
    private final AccountId createId;

    @NonNull
    private final WxUnionId unionId;

    private WxUserNickname nickname;
    private WxUserAvatar avatar;
    private WxUserSex sex;
    private WxUserProvince province;
    private WxUserCity city;
    private WxUserCountry country;
    private WxUserSessionKey sessionKey;

    private WxTaskUserOpenId wxTaskUserOpenId;
    private WxLoveUserOpenId wxLoveUserOpenId;

    @Override
    public boolean hasSameRootAttribute(WxUser other) {
        return other != null &&
                Objects.equals(nickname, other.nickname) &&
                Objects.equals(avatar, other.avatar) &&
                Objects.equals(sex, other.sex) &&
                Objects.equals(province, other.province) &&
                Objects.equals(city, other.city) &&
                Objects.equals(country, other.country) &&
                Objects.equals(sessionKey, other.sessionKey);
    }
}
