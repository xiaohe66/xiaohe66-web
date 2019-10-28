package com.xiaohe66.web.code.security.service;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.PwdUtils;
import com.xiaohe66.web.base.util.RegexUtils;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.cache.CacheHelper;
import com.xiaohe66.web.code.org.dto.UserDto;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.security.auth.entity.EmailAuthCode;
import com.xiaohe66.web.code.security.auth.helper.AuthCodeHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaohe
 * @time 17-11-05 005
 */
@Service
@Slf4j
public class LoginService {

    private static final String REGISTER_VERIFY = "http://xiaohe66.com/org/usr/verify/";

    private UserService userService;
    private UserRoleService userRoleService;
    private AuthService authService;

    public LoginService(UserService userService, UserRoleService userRoleService, AuthService authService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.authService = authService;
    }

    /**
     * 注册准备方法，发送注册邮件
     *
     * @param user Usr
     * @param code 当前操作的图片验证码
     */
    public void registerPrepare(User user, String code) {
        if (!AuthCodeHelper.verifyImgCode(code)) {
            throw new XhWebException(CodeEnum.B2_TOKEN_ERROR);
        }

        String userName = user.getUsrName();
        String email = user.getEmail();
        Check.notEmpty(userName);
        Check.notEmpty(email);

        if (!RegexUtils.testUsrName(userName) || !RegexUtils.testEmail(email)) {
            throw new XhWebException(CodeEnum.B1_ILLEGAL_PARAM);
        }

        if (userService.isExistUserName(userName)) {
            throw new XhWebException(CodeEnum.B1_OBJ_ALREADY_EXIST, "用户名已存在 : " + userName);
        }

        if (userService.isExistEmail(email)) {
            throw new XhWebException(CodeEnum.B1_OBJ_ALREADY_EXIST, "邮箱已存在 : " + email);
        }

        String token = PwdUtils.createToken();

        String link = REGISTER_VERIFY + token;

        log.debug("发送link邮件，内容为: {}", link);

        //发送邮件
        authService.sendLink(link, user.getEmail(), user.getUsrName(), "注册");

        CacheHelper.put30(token, user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void register(String token) {
        User user = CacheHelper.get30(token);

        if (user == null) {
            throw new XhWebException(CodeEnum.B2_TOKEN_TIME_OUT);
        }

        user.setUsrPwd(PwdUtils.hashPassword(user.getUsrPwd()));
        try {
            userService.save(user);
            userRoleService.addDefaultUsrRole(user.getId());
        } catch (Exception e) {
            throw new XhWebException(CodeEnum.RUNTIME_EXCEPTION, "注册失败", e);
        }

        CacheHelper.remove30(token);
    }

    public void updatePwdPrepare(String email, String code) {
        Check.notEmpty(email);
        Check.notEmpty(code);
        if (!AuthCodeHelper.verifyImgCode(code)) {
            throw new XhWebException(CodeEnum.B2_TOKEN_ERROR);
        }

        User user = userService.getByEmail(email);
        if (user == null) {
            throw new XhWebException(CodeEnum.B1_OBJ_NOT_EXIST);
        }

        WebUtils.setSessionAttr(Final.Str.SESSION_UPDATE_PWD_USR_KEY, user);

        EmailAuthCode emailAuthCode = AuthCodeHelper.createEmailAuthCode(email);

        log.debug("发送验证码邮件，内容为：{}", emailAuthCode.getCode());
        authService.sendAuthCode(emailAuthCode.getCode(), email, user.getUsrName(), "修改密码");
    }

    public void updatePwd(String password, String code) {
        Check.notEmpty(password);
        Check.notEmpty(code);
        if (!AuthCodeHelper.verifyEmailCode(code)) {
            throw new XhWebException(CodeEnum.B2_TOKEN_ERROR);
        }

        User user = WebUtils.getSessionAttr(Final.Str.SESSION_UPDATE_PWD_USR_KEY);

        user.setUsrPwd(PwdUtils.hashPassword(password));

        userService.updateById(user);
    }

    public UserDto login(String loginName, String userPwd) {

        log.debug("loginName={}", loginName);

        Check.notEmpty(loginName, "loginName");
        Check.notEmpty(loginName, "userPwd");

        Subject subject = SecurityUtils.getSubject();
        UserDto currentUsr = (UserDto) subject.getSession().getAttribute(Final.Str.SESSION_UER_KEY);

        if (currentUsr != null && loginName.equals(currentUsr.getUsrName())) {
            //该用户已经登录
            log.debug("This user({}) is logged in", loginName);
            return currentUsr;
        }

        //登录名中存在@，则为邮箱账号
        User dbUsr = loginName.contains("@") ? userService.getByEmail(loginName) : userService.getByUserName(loginName);

        if (dbUsr == null) {
            throw new XhWebException(CodeEnum.B1_OBJ_NOT_EXIST, "用户不存在 : " + loginName);
        }

        //验证密码
        if (!PwdUtils.passwordsMatch(userPwd, dbUsr.getUsrPwd())) {
            throw new XhWebException(CodeEnum.B2_TOKEN_ERROR, "password is wrong");
        }
        return this.loginToShiro(dbUsr);
    }

    private UserDto loginToShiro(User user) {
        log.info("登录到系统：{}", user.getUsrName());
        String userName = user.getUsrName();
        String userPwd = user.getUsrPwd();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, userPwd);

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            log.debug("login failing:userName:{}, userPwd:{}", userName, userPwd);
            return null;
        }
        //构建dto
        UserDto dtoUsr = new UserDto(user);
        //注入session
        subject.getSession().setAttribute(Final.Str.SESSION_UER_KEY, dtoUsr);
        return dtoUsr;
    }

    public void logout() {
        log.debug("注销登录");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        subject.getSession().removeAttribute(Final.Str.SESSION_UER_KEY);
    }

    public boolean isLogin() {
        return SecurityUtils.getSubject().isAuthenticated();
    }
}
