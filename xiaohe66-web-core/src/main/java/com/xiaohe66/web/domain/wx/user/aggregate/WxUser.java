package com.xiaohe66.web.domain.wx.user.aggregate;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.wx.user.value.WxLoveUserOpenId;
import com.xiaohe66.web.domain.wx.user.value.WxTodoUserOpenId;
import com.xiaohe66.web.domain.wx.user.value.WxUnionId;
import com.xiaohe66.web.domain.wx.user.value.WxUserId;
import com.xiaohe66.web.integration.domain.Aggregate;
import com.xiaohe66.web.integration.value.MobileNo;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * @author xiaohe
 * @since 2021.08.11 17:48
 */
@Data
@Builder
public class WxUser implements Aggregate<WxUser, WxUserId> {

    @NonNull
    private final WxUserId id;

    @NonNull
    private final AccountId accountId;

    @NonNull
    private final WxUnionId unionId;
    private MobileNo phone;

    private String nickname;
    /**
     * TODO : 性别使用值对象还是 枚举？
     */
    private Integer sex;
    private String province;
    private String city;
    private String country;
    private String avatarUrl;
    private String sessionKey;

    private WxTodoUserOpenId wxTodoUserOpenId;
    private WxLoveUserOpenId wxLoveUserOpenId;


    @Override
    public WxUserId getId() {
        return id;
    }

    @Override
    public boolean hasDiffRoot(WxUser other) {
        return !(isSameIdentity(other) &&
                accountId.equals(other.getAccountId()) &&
                unionId.equals(other.getUnionId()) &&
                phone.equals(other.getPhone()) &&
                sex.equals(other.getSex()) &&
                province.equals(other.getProvince()) &&
                city.equals(other.getCity()) &&
                country.equals(other.getCountry()) &&
                avatarUrl.equals(other.getAvatarUrl()) &&
                sessionKey.equals(other.getSessionKey()));
    }
}
