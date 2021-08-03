package com.xiaohe66.web.code.wx.service;

import com.xiaohe66.common.api.ApiException;
import com.xiaohe66.web.base.annotation.PrintLog;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.code.org.dto.CurrentUser;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.org.po.WxUser;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.org.service.WxUserService;
import com.xiaohe66.web.code.security.service.LoginService;
import com.xiaohe66.web.code.wx.api.WxApiClient;
import com.xiaohe66.web.code.wx.api.model.WxCode2SessionModel;
import com.xiaohe66.web.code.wx.api.request.WxCode2SessionRequest;
import com.xiaohe66.web.code.wx.api.response.WxCode2SessionResponse;
import com.xiaohe66.web.config.WxConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaohe
 * @time 2019.12.10 15:17
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WxLoginService {

    private final LoginService loginService;
    private final UserService userService;
    private final WxUserService wxUserService;
    private final WxApiClient client;

    private final WxConfig config;

    @PrintLog
    public Result login(WxUser wxUser, String code) {

        WxCode2SessionModel model = new WxCode2SessionModel();
        model.setAppId(config.getAppId());
        model.setAppSecret(config.getAppSecret());
        model.setCode(code);

        WxCode2SessionRequest request = new WxCode2SessionRequest();
        request.setModel(model);

        WxCode2SessionResponse response;
        try {
            response = client.execute(request);

        } catch (ApiException e) {
            log.error("微信登录失败, code : {}", code, e);
            return Result.err("微信登录失败");
        }

        log.info("微信登录接口返回 : {}", response);

        // note : 微信接口文档写了会返回 errcode，但实际上并没有返回
        if (response != null && response.getOpenId() != null) {

            String openId = response.getOpenId();

            wxUser.setOpenId(openId);
            wxUser.setUnionId(response.getUnionId());
            wxUser.setSessionKey(response.getSessionKey());

            WxUser dbWxUser = wxUserService.getByOpenId(openId);
            if (dbWxUser == null) {

                // 注册账号, 自己调用自己的方法会导致事务失效，因此需要获取自己的代理类后再调用
                WxLoginService currentProxy = (WxLoginService) AopContext.currentProxy();
                dbWxUser = currentProxy.register(wxUser);

            } else {
                wxUser.setId(dbWxUser.getId());
                wxUserService.updateById(wxUser);
            }

            CurrentUser userDto = loginService.login(dbWxUser.getUserId());

            //注入session
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute(Final.SessionKey.CURRENT_LOGIN_WXUSER, wxUser);

            //String token = UUID.randomUUID().toString().replace("-", "");

            // 保存 token
            //CacheHelper.put7d(token, userDto.getId());

            //return Result.ok(token);

            Map<String, Object> map = new HashMap<>();
            map.put("userDto", userDto);
            map.put("sessionId", session.getId());

            return Result.ok(map);
        }

        log.error("微信登录失败, code : {}", code);
        return Result.err("微信登录失败");
    }


    @Transactional(rollbackFor = Exception.class)
    public WxUser register(WxUser wxUser) {

        String openId = wxUser.getOpenId();

        User user = new User();

        String userName = String.valueOf(System.currentTimeMillis() / 1000);

        String pwd = String.valueOf(openId.hashCode());
        String email = userName + "@xiaohe66.com";

        user.setUserName(userName);
        user.setUserPwd(pwd);
        user.setEmail(email);

        log.info("微信新用户注册账号, openId : {}", openId);
        loginService.register(user);

        wxUser.setUserId(user.getId());

        wxUserService.save(wxUser);
        log.info("微信新用户注册账号成功, openId : {}", openId);

        return wxUser;
    }
}
