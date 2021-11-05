package com.xiaohe66.web.application;

import com.xiaohe66.common.api.ApiException;
import com.xiaohe66.common.dto.R;
import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.application.config.WxConfig;
import com.xiaohe66.web.application.convert.WxLoginDataConverter;
import com.xiaohe66.web.application.request.WxLoginRequest;
import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.domain.account.service.AccountService;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.account.value.AccountName;
import com.xiaohe66.web.domain.sys.sec.ex.LoginException;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.repository.WxUserRepository;
import com.xiaohe66.web.domain.wx.user.value.WxUnionId;
import com.xiaohe66.web.domain.wx.user.value.WxUserId;
import com.xiaohe66.web.infrastructure.acl.wx.WxApiClient;
import com.xiaohe66.web.infrastructure.acl.wx.model.WxCode2SessionModel;
import com.xiaohe66.web.infrastructure.acl.wx.request.WxCode2SessionRequest;
import com.xiaohe66.web.infrastructure.acl.wx.response.WxCode2SessionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xiaohe
 * @time 2019.12.10 15:17
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WxLoginService {

    public static final String SESSION_KEY_WX_USER = "currentWxUser";

    private final LoginService loginService;
    private final AccountService accountService;
    private final WxUserRepository wxUserRepository;
    private final WxApiClient client;

    private final SecurityService sessionService;
    private final WxLoginDataConverter wxLoginDataConverter;

    private final WxConfig config;

    public R<String> login(WxLoginRequest loginRequest, String code) {

        WxCode2SessionResponse response;
        try {
            response = requestCode2Session(code);
            log.info("wx code2session response : {}", response);

        } catch (ApiException e) {
            log.error("wx code2session error, code : {}", code, e);
            return R.err("微信登录失败");
        }

        // note : 微信接口文档写了会返回 errcode，但实际上并没有返回
        if (response == null || response.getUnionId() == null) {

            log.error("wx code2session fail, code : {}", code);

            return R.err("微信登录失败");
        }

        WxUnionId unionId = new WxUnionId(response.getUnionId());
        WxUser wxUser = wxUserRepository.getByUnionId(unionId);
        if (wxUser == null) {

            log.info("register wx user, unionId : {}", response.getUnionId());

            Account account = registerAccount(loginRequest, response);

            wxUser = WxUser.builder()
                    .id(new WxUserId(IdWorker.getId()))
                    .accountId(account.getId())
                    .unionId(unionId)
                    .build();

            log.info("register wx user success, unionId : {}, accountId : {}",
                    response.getUnionId(),
                    account.getId().getValue());
        }

        wxLoginDataConverter.copyValueToWxUser(wxUser, loginRequest);
        wxUserRepository.save(wxUser);

        try {
            loginService.login(wxUser.getAccountId());

        } catch (LoginException e) {
            log.error("登录失败", e);
        }

        log.info("wx account login success, unionId : {}", response.getUnionId());

        //注入session
        sessionService.setAttribute(SESSION_KEY_WX_USER, wxUser);

        return R.ok(sessionService.getSessionId());

    }

    private WxCode2SessionResponse requestCode2Session(String code) throws ApiException {
        WxCode2SessionModel model = new WxCode2SessionModel();
        model.setAppId(config.getAppId());
        model.setAppSecret(config.getAppSecret());
        model.setCode(code);

        WxCode2SessionRequest request = new WxCode2SessionRequest();
        request.setModel(model);

        return client.execute(request);
    }


    public Account registerAccount(WxLoginRequest loginRequest, WxCode2SessionResponse response) {

        Account account = Account.builder()
                .id(new AccountId(IdWorker.getId()))
                .name(new AccountName(loginRequest.getNickname()))
                .build();

        accountService.register(account);

        return account;
    }
}
