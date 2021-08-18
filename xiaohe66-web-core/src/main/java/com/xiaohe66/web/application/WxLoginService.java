package com.xiaohe66.web.application;

import com.xiaohe66.web.domain.account.service.AccountService;
import com.xiaohe66.web.domain.account.service.WxUserService;
import lombok.RequiredArgsConstructor;

/**
 * @author xiaohe
 * @since 2021.08.11 17:53
 */
@RequiredArgsConstructor
public class WxLoginService {

    private final AccountService accountService;
    private final WxUserService wxUserService;

    public void login(){

    }

}
