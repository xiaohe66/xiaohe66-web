package com.xiaohe66.web.aop;

import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.org.dto.UserDto;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.security.service.LoginService;
import com.xiaohe66.web.code.sys.helper.SysCfgHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 *
 * 在开发环境时，会自动登录，需要在jvm参数中添加
 * <code>-Dspring.profiles.active="dev"</code>
 *
 * @author xiaohe
 * @time 2019.10.19 22:05
 */
@Aspect
@Slf4j
@Profile("dev")
@Component
public class DevUserAutoLoginAsp {

    private LoginService loginService;
    private UserService userService;

    private Method loginMethod;

    public DevUserAutoLoginAsp(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
        try {
            loginMethod = LoginService.class.getDeclaredMethod("loginToShiro", User.class);
            loginMethod.setAccessible(true);

            log.info("开发环境自动登录模块初始化成功");
        } catch (NoSuchMethodException ignored) {
            log.error("无法实例化开发环境自动登录模块");
        }
    }

    @Pointcut("execution( * com.xiaohe66.web.controller.UsrPageController.register(..) )")
    private void loginAop() {
        // 开发环境自动登录切面
    }

    @Before("loginAop()")
    public void before() {
        if (!loginService.isLogin()) {

            User id = userService.getById(SysCfgHelper.findXhUsrId());
            User user = userService.getById(id);

            try {
                loginMethod.invoke(loginService, user);
            } catch (Exception e) {
                log.error("无法自动登录,message : {}", e.getMessage());
            }
        }
    }
}
