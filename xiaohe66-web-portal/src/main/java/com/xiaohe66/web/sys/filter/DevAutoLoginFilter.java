package com.xiaohe66.web.sys.filter;

import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.code.org.dto.UserDto;
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
 * @time 2019.12.09 11:07
 */
@Slf4j
public class DevAutoLoginFilter implements Filter {

    @Autowired
    private LoginService loginService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化开发登录器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!SecurityUtils.getSubject().isAuthenticated()) {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String uri = request.getRequestURI();

            if (!"/wx/login".equals(uri)) {
                log.info("开发登录器自动登录");
                try {
                    UserDto user = loginService.login(Final.User.ADMIN_USER_ID);
                    log.info("登录成功, userDto : {}", user);

                } catch (Exception e) {
                    log.error("开发环境自动登录失败", e);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("销毁开发登录器");
    }
}
