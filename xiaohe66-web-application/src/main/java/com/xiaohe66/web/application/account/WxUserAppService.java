package com.xiaohe66.web.application.account;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.account.bo.WxUserUpdateBo;
import com.xiaohe66.web.application.account.convert.WxUserBoConverter;
import com.xiaohe66.web.application.aop.annotation.NeedRoles;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.sys.sec.value.RoleName;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.repository.WxUserRepository;
import com.xiaohe66.web.domain.wx.user.service.WxUserService;
import lombok.NonNull;
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

    private final WxUserBoConverter boConverter;
    private final WxUserRepository wxUserRepository;
    private final WxUserService wxUserService;
    private final SecurityService securityService;

    @NeedRoles(RoleName.WX_ROLE_VALUE)
    public R<Void> updateCurrent(@NonNull WxUserUpdateBo bo) {

        /* TODO : 限制只能微信用户操作 */
        AccountId currentAccountId = securityService.getCurrentAccountId();
        WxUser wxUser = wxUserRepository.getByAccountId(currentAccountId);

        boConverter.setUserInfo(wxUser,bo);
        wxUserService.update(wxUser);

        return R.ok();
    }

}
