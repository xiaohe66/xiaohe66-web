package com.xiaohe66.web.domain.wx.user.service;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.repository.WxUserRepository;
import com.xiaohe66.web.domain.wx.user.value.WxUserAvatarUrl;
import com.xiaohe66.web.domain.wx.user.value.WxUserNickname;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xiaohe
 * @since 2021.08.11 17:54
 */
@AllArgsConstructor
@Service
@Slf4j
public class WxUserService {

    private final WxUserRepository wxUserRepository;

    public void saveWxUser(WxUser wxUser) {

        wxUserRepository.save(wxUser);

        log.info("save wx user success, unionId : {}, accountId : {}",
                wxUser.getUnionId(),
                wxUser.getAccountId());
    }

    public void updateUserInfoByAccountId(AccountId accountId,
                                          WxUserNickname nickname,
                                          WxUserAvatarUrl avatarUrl) {

        WxUser wxUser = wxUserRepository.getByAccountId(accountId);
        if (wxUser == null) {
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND_ACCOUNT);
        }

        log.info("update wx user info, nickname : {}->{}, avatarUrl : {}->{}",
                wxUser.getNickname(),
                nickname,
                wxUser.getAvatarUrl(),
                avatarUrl);

        wxUser.setNickname(nickname);
        wxUser.setAvatarUrl(avatarUrl);

        wxUserRepository.save(wxUser);
    }

}
