package com.xiaohe66.web.gateway.http.sec;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.LoginService;
import com.xiaohe66.web.domain.account.value.AccountName;
import com.xiaohe66.web.domain.sys.sec.ex.LoginException;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 普通登录
 *
 * @author xiaohe66
 * @version 2.0
 * @since 2021.11.01 11:44
 */
@RestController
@RequestMapping("/sec")
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final SecurityService securityService;

    @GetMapping("/islogin")
    public R<Boolean> isLogin() {
        return R.ok(securityService.isLogin());
    }

    @GetMapping("/getsessionid")
    public R<Boolean> getSessionId() {
        return R.ok(securityService.isLogin());
    }

    @PostMapping("/login")
    public R<String> login(String loginName, String password) {

        if (StringUtils.isBlank(loginName)) {
            return R.err("登录名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return R.err("密码不能为空");
        }

        AccountName accountName = new AccountName(loginName);

        try {
            loginService.login(accountName, password);
            return R.ok(securityService.getSessionId());

        } catch (LoginException e) {
            log.info("login fail", e);
            return R.err("登录失败");
        }
    }

    @PostMapping
    public R<Void> logout() {

        securityService.logout();

        return R.ok();
    }

    //    @Page("/findPwd")
    public String findPwdPage(Model model) {
        model.addAttribute("title", "修改密码");
        return "org/find_pwd";
    }

    // 用于邮箱找回密码
    /*@Post("/pwd")
    public void updatePwdPrepare(String email, String code) {
        loginService.updatePwdPrepare(email, code);
    }

    @Put("/pwd")
    public void updatePwd(String password, String code) {
        loginService.updatePwd(password, code);
    }*/

    // 用于注册发邮件
//    @Post("/register")
    /*public Boolean register(User user, String code) {
        try {
            loginService.registerPrepare(user, code);
        } catch (MissingParamException e) {
            throw new MsgException(e.getCode(), e.getMessage());
        }
        return true;
    }*/

    // 用于注册验证
//    @Page("/register/{token}")
    /*public String registerVerify(@PathVariable String token) {
        loginService.register(token);
        return "redirect:/";
    }*/


}
