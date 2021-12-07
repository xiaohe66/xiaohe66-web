package com.xiaohe66.web.domain.wx.user.aggregate;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.sys.sec.value.RoleId;
import com.xiaohe66.web.domain.wx.user.value.WxLoveUserOpenId;
import com.xiaohe66.web.domain.wx.user.value.WxTaskUserOpenId;
import com.xiaohe66.web.domain.wx.user.value.WxUnionId;
import com.xiaohe66.web.domain.wx.user.value.WxUserAvatarUrl;
import com.xiaohe66.web.domain.wx.user.value.WxUserId;
import com.xiaohe66.web.domain.wx.user.value.WxUserNickname;
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
@ToString
@AllArgsConstructor
public class WxUser implements Aggregate<WxUser, WxUserId> {

    public static final RoleId ROLE_ID = new RoleId(2);
    public static final RoleId LOVE_ROLE_ID = new RoleId(3);
    public static final RoleId TASK_ROLE_ID = new RoleId(4);

    @NonNull
    private final WxUserId id;

    @NonNull
    private final AccountId accountId;

    @NonNull
    private final WxUnionId unionId;

    @Setter
    private WxUserNickname nickname;

    @Setter
    private WxUserAvatarUrl avatarUrl;

    @Setter
    private WxTaskUserOpenId wxTaskUserOpenId;

    @Setter
    private WxLoveUserOpenId wxLoveUserOpenId;

    @Override
    public boolean hasSameRootAttribute(WxUser other) {
        return other != null &&
                Objects.equals(nickname, other.nickname) &&
                Objects.equals(avatarUrl, other.avatarUrl) &&
                Objects.equals(avatarUrl, other.getAvatarUrl());
    }
}
