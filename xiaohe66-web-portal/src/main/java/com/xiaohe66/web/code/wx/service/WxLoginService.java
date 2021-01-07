package com.xiaohe66.web.code.wx.service;

import com.xiaohe66.common.net.ex.RequesterException;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.cache.CacheHelper;
import com.xiaohe66.web.code.org.dto.UserDto;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.org.po.WxUser;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.org.service.WxUserService;
import com.xiaohe66.web.code.security.service.LoginService;
import com.xiaohe66.web.code.wx.request.WxCode2SessionRequest;
import com.xiaohe66.web.code.wx.requester.WxCode2SessionRequester;
import com.xiaohe66.web.code.wx.response.WxCode2SessionResponse;
import com.xiaohe66.web.config.WxConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author xiaohe
 * @time 2019.12.10 15:17
 */
@Service
@Slf4j
public class WxLoginService {

    private LoginService loginService;

    private UserService userService;

    private WxUserService wxUserService;

    private final WxCode2SessionRequester requester;

    private final WxConfig config;

    public WxLoginService(LoginService loginService, UserService userService, WxUserService wxUserService, WxCode2SessionRequester requester, WxConfig config) {
        this.loginService = loginService;
        this.userService = userService;
        this.wxUserService = wxUserService;
        this.requester = requester;
        this.config = config;
    }

    public Result login(String code) {

        WxCode2SessionRequest request = new WxCode2SessionRequest();
        request.setAppId(config.getAppId());
        request.setAppSecret(config.getAppSecret());
        request.setCode(code);

        WxCode2SessionResponse response;
        try {
            response = requester.call(request);

        } catch (RequesterException e) {
            log.error("微信登录失败, code : {}", e);
            return Result.err("微信登录失败");
        }

        log.info("微信登录接口返回 : {}", response);

        if (response != null && response.getErrCode() == WxCode2SessionResponse.ErrCode.OK.getCode()) {

            String openId = response.getOpenId();

            WxUser wxUser = wxUserService.getByOpenId(openId);
            if (wxUser == null) {

                // 注册账号, 自己调用自己的方法会导致事务失效，因此需要获取自己的代理类后再调用
                WxLoginService currentProxy = (WxLoginService) AopContext.currentProxy();
                wxUser = currentProxy.register(openId);
            }

            UserDto userDto = loginService.login(wxUser.getUserId());

            String token = UUID.randomUUID().toString();

            // 保存 token
            CacheHelper.put7d(token, userDto.getId());

            return Result.ok(token);
        }

        log.error("微信登录失败, code : {}", code);
        return Result.err("微信登录失败");
    }


    @Transactional(rollbackFor = Exception.class)
    public WxUser register(String openId) {

        User user = new User();

        String pwd = String.valueOf(openId.hashCode());
        String email = openId + "@xiaohe66.com";

        user.setUserName(openId);
        user.setUserPwd(pwd);
        user.setEmail(email);

        log.info("微信新用户注册账号, openId : {}", openId);
        loginService.register(user);

        WxUser wxUser = new WxUser();
        wxUser.setUserId(user.getId());
        wxUser.setOpenId(openId);

        wxUserService.save(wxUser);
        log.info("微信新用户注册账号成功, openId : {}", openId);

        return wxUser;
    }
}
