package com.xiaohe66.web.code.security.auth.shrio;

import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.cache.CacheHelper;
import com.xiaohe66.web.code.security.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaohe
 * @time 2021.01.07 10:42
 */
@Slf4j
public class TokenLoginExtend implements ShiroFilterIsAccessAllowedHandler.Extend {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean before(HttpServletRequest request, HttpServletResponse response) {
        if (!SecurityUtils.getSubject().isAuthenticated()) {

            // 排除掉
            String token = request.getHeader("token");
            if (Check.isNotEmpty(token)) {

                Integer userId = CacheHelper.get7d(token);
                if (userId != null) {
                    loginService.login(userId);
                }
            }
        }
        return true;
    }
}
