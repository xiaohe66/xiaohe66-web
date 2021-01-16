package com.xiaohe66.web.sys.filter;

import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.cache.CacheHelper;
import com.xiaohe66.web.code.security.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author xiaohe
 * @time 2021.01.07 10:42
 */
@Slf4j
public class TokenLoginFilter implements Filter {

    @Autowired
    private LoginService loginService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化 token 登录过渡器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!SecurityUtils.getSubject().isAuthenticated()) {

            HttpServletRequest request = (HttpServletRequest) servletRequest;

            // 排除掉
            String token = request.getHeader("token");
            if (Check.isNotEmpty(token)) {

                Integer userId = CacheHelper.get7d(token);
                if (userId != null) {
                    loginService.login(userId);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
