package com.xiaohe66.web.application.account;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.account.bo.WxUserUpdateBo;
import com.xiaohe66.web.application.aop.annotation.NeedLogin;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.wx.user.service.WxUserService;
import com.xiaohe66.web.domain.wx.user.value.WxUserAvatarUrl;
import com.xiaohe66.web.domain.wx.user.value.WxUserNickname;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xiaohe
 * @since 2021.12.07 12:14
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WxUserAppService {

    private final WxUserService wxUserService;
    private final SecurityService securityService;

    @NeedLogin
    public R<Void> updateCurrent(WxUserUpdateBo bo) {

        /* TODO : 限制只能微信用户操作 */

        AccountId currentAccountId = securityService.getCurrentAccountId();

        WxUserNickname nickname = bo.getNickname() == null ? null : new WxUserNickname(bo.getNickname());
        WxUserAvatarUrl avatarUrl = bo.getAvatarUrl() == null ? null : new WxUserAvatarUrl(bo.getAvatarUrl());

        wxUserService.updateUserInfoByAccountId(currentAccountId, nickname, avatarUrl);

        return R.ok();
    }

}
