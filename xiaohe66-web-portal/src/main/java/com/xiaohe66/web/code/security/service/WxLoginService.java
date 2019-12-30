package com.xiaohe66.web.code.security.service;

import com.xiaohe66.web.code.org.dto.UserDto;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.org.po.WxUser;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.org.service.WxUserService;
import com.xiaohe66.web.code.wx.request.WxCode2SessionRequest;
import com.xiaohe66.web.code.wx.requester.WxCode2SessionRequester;
import com.xiaohe66.web.code.wx.response.WxCode2SessionResponse;
import com.xiaohe66.web.config.WxConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaohe
 * @time 2019.12.10 15:17
 */
//@Service
@Slf4j
public class WxLoginService {

    private LoginService loginService;

    private UserService userService;

    private WxUserService wxUserService;

    private WxCode2SessionRequester requester;

    private WxConfig config;

    public WxLoginService(LoginService loginService, UserService userService, WxUserService wxUserService, WxCode2SessionRequester requester) {
        this.loginService = loginService;
        this.userService = userService;
        this.wxUserService = wxUserService;
        this.requester = requester;
    }

    public String login(String code) {
        WxCode2SessionRequest request = new WxCode2SessionRequest();
        request.setAppId(config.getAppId());
        request.setAppSecret(config.getAppSecret());
        request.setCode(code);

        WxCode2SessionResponse response = requester.call(request);
        if (response != null && response.getErrCode() == WxCode2SessionResponse.ErrCode.OK.getCode()) {

            String openId = response.getOpenId();

            WxUser wxUser = wxUserService.getByOpenId(openId);
            if (wxUser == null) {
                // 注册账号
                wxUser = register(openId);
            }

            UserDto userDto = loginService.login(wxUser.getUserId());

            // todo : 保存微信用户的session

            return openId;

        } else {
            log.error("微信登录失败, code : {}", code);
            return null;
        }
    }


    @Transactional
    public WxUser register(String openId) {
        // todo : 创建账号
        User user = new User();

        String userName = String.valueOf(System.currentTimeMillis());
        String pwd = String.valueOf(userName.hashCode());
        String email = userName + "@xiaohe66.com";

        user.setUserName(userName);
        user.setUserPwd(pwd);
        user.setEmail(email);

        log.info("微信新用户注册账号, openId : {}, userName : {}, userPwd : {}, email : {}", openId, userName, pwd, email);

//        wxUser.setOpenId(openId);
        return null;
    }
}
