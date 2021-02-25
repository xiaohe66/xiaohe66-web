package com.xiaohe66.web.code.security.auth.shrio;

import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.code.org.dto.CurrentUser;
import com.xiaohe66.web.code.security.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaohe
 * @time 2019.12.09 11:07
 */
@Slf4j
public class DevAutoLoginExtend implements ShiroFilterIsAccessAllowedHandler.Extend {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean before(HttpServletRequest request, HttpServletResponse response) {
        if (!SecurityUtils.getSubject().isAuthenticated()) {

            String uri = request.getRequestURI();

            if (!"/wx/login".equals(uri)) {
                log.info("开发登录器自动登录");
                try {
                    CurrentUser user = loginService.login(Final.User.ADMIN_USER_ID);
                    log.info("登录成功, userDto : {}", user);

                } catch (Exception e) {
                    log.error("开发环境自动登录失败", e);
                }
            }
        }
        return true;
    }
}
