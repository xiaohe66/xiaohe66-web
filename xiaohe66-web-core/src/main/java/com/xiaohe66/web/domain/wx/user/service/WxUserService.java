package com.xiaohe66.web.domain.wx.user.service;

import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.repository.WxUserRepository;
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

}
