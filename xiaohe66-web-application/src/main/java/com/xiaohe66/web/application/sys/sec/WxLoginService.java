package com.xiaohe66.web.application.sys.sec;

import com.xiaohe66.common.api.ApiException;
import com.xiaohe66.common.dto.R;
import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.application.sys.sec.bo.WxLoginBo;
import com.xiaohe66.web.application.sys.sec.convert.WxLoginDataConverter;
import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.domain.account.service.AccountService;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.account.value.AccountName;
import com.xiaohe66.web.domain.account.value.AccountPassword;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.repository.WxUserRepository;
import com.xiaohe66.web.domain.wx.user.service.WxUserService;
import com.xiaohe66.web.domain.wx.user.value.WxUnionId;
import com.xiaohe66.web.domain.wx.user.value.WxUserId;
import com.xiaohe66.web.infrastructure.acl.wx.WxApiClient;
import com.xiaohe66.web.infrastructure.acl.wx.model.WxCode2SessionModel;
import com.xiaohe66.web.infrastructure.acl.wx.request.WxCode2SessionRequest;
import com.xiaohe66.web.infrastructure.acl.wx.response.WxCode2SessionResponse;
import com.xiaohe66.web.integration.config.WxConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author xiaohe
 * @time 2019.12.10 15:17
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WxLoginService {

    private final LoginService loginService;
    private final AccountService accountService;
    private final WxUserService wxUserService;
    private final WxUserRepository wxUserRepository;
    private final WxApiClient client;

    private final SecurityService securityService;
    private final WxLoginDataConverter wxLoginDataConverter;

    private final WxConfig wxConfig;

    public R<String> login(WxLoginBo loginBo) {

        // 1. ACL 调用微信
        WxCode2SessionResponse response;
        try {
            response = requestCode2Session(loginBo.getType(), loginBo.getCode());
            log.info("wx code2session response : {}", response);

        } catch (ApiException e) {
            log.error("wx code2session error, code : {}", loginBo.getCode(), e);
            return R.err("微信登录失败");
        }

        // 2. 更新账号数据, 不存在则创建
        WxUnionId unionId = new WxUnionId(response.getUnionId());
        WxUser wxUser = wxUserRepository.getByUnionId(unionId);
        if (wxUser == null) {

            log.info("register wx account, unionId : {}", response.getUnionId());

            if (StringUtils.isBlank(loginBo.getNickname())) {
                loginBo.setNickname(IdWorker.genIdStr());
            }

            Account account = registerAccount(loginBo);

            log.info("save wxUser, unionId : {}", response.getUnionId());

            wxUser = WxUser.builder()
                    .id(new WxUserId(IdWorker.genId()))
                    .accountId(account.getId())
                    .unionId(unionId)
                    .build();
        }

        wxLoginDataConverter.copyValueToWxUser(wxUser, loginBo);
        wxLoginDataConverter.setOpenId(wxUser, loginBo.getType(), response.getOpenId());

        wxUserService.saveWxUser(wxUser);

        // 3.录到系统
        loginService.login(wxUser.getAccountId());

        log.info("wx account login success, unionId : {}", response.getUnionId());

        // TODO : 注入session
        //securityService.setAttribute(SESSION_KEY_WX_USER, wxUser);

        return R.ok(securityService.getSessionId());

    }

    private WxCode2SessionResponse requestCode2Session(WxLoginBo.Type type, String code) throws ApiException {

        WxConfig.Prop prop = type == WxLoginBo.Type.TASK ?
                wxConfig.getTask() :
                wxConfig.getLove();

        WxCode2SessionModel model = new WxCode2SessionModel();
        model.setAppId(prop.getAppId());
        model.setAppSecret(prop.getAppSecret());
        model.setCode(code);

        WxCode2SessionRequest request = new WxCode2SessionRequest();
        request.setModel(model);

        WxCode2SessionResponse response = client.execute(request);

        // note : 微信接口文档写了会返回 errcode，但实际上并没有返回
        if (response == null) {
            throw new ApiException("wx code2session response is null");
        }

        if (response.getUnionId() == null) {

            log.error("wx code2session unionId is null, code : {}, response : {}", code, response.getResponse());
            throw new ApiException("response.unionId is null");
        }

        return response;
    }


    protected Account registerAccount(WxLoginBo loginBo) {

        Account account = Account.builder()
                .id(new AccountId(IdWorker.genId()))
                .name(new AccountName(loginBo.getNickname()))
                .password(AccountPassword.EMPTY)
                .build();

        accountService.register(account);

        return account;
    }
}
