package com.xiaohe66.web.code.security.service;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.MsgException;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.PwdUtils;
import com.xiaohe66.web.base.util.RegexUtils;
import com.xiaohe66.web.base.util.StrUtils;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.cache.CacheHelper;
import com.xiaohe66.web.code.org.dto.UsrDto;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.security.auth.entity.EmailAuthCode;
import com.xiaohe66.web.code.security.auth.helper.AuthCodeHelper;
import com.xiaohe66.web.code.sys.helper.EmailHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaohe
 * @time 17-11-05 005
 */
@Service
public class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private static final String REGISTER_VERIFY = "http://xiaohe66.com/org/usr/verify/";

    /**
     * 锁
     */
    private final Object REGISTER_LOCK = new Object();

    @Autowired
    private UserService userService;

    @Autowired
    private UsrRoleService roleService;

    /**
     * 注册准备方法，发送注册邮件
     *
     * @param user Usr
     * @param code 当前操作的图片验证码
     */
    public void registerPrepare(User user, String code) {
        if (!AuthCodeHelper.verifyImgCode(code)) {
            throw new MsgException(CodeEnum.AUTH_CODE_ERR, "code is wrong");
        }

        String usrName = user.getUsrName();
        String email = user.getEmail();
        Check.notEmptyCheck(usrName, email);

        if (!RegexUtils.testUsrName(usrName) || !RegexUtils.testEmail(email)) {
            throw new MsgException(CodeEnum.FORMAT_ERROR);
        }

        if (userService.usrNameIsExist(usrName)) {
            throw new MsgException(CodeEnum.OBJ_ALREADY_EXIST, "usrName is exist");
        }

        if (userService.emailIsExist(email)) {
            throw new MsgException(CodeEnum.OBJ_ALREADY_EXIST, "email is exist");
        }

        String token = PwdUtils.createToken();

        String link = REGISTER_VERIFY + token;

        logger.debug("发送link邮件，内容为：" + link);

        //发送邮件
        EmailHelper.sendLink(link, user.getEmail(), user.getUsrName(), "注册");

        CacheHelper.put30(token, user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void register(String token) {
        User user = CacheHelper.get30(token);

        if (user == null) {
            throw new MsgException(CodeEnum.TOKEN_TIME_OUT);
        }
        CacheHelper.remove30(token);

        user.setUsrPwd(PwdUtils.hashPassword(user.getUsrPwd()));
        try {
            userService.save(user);
        } catch (Exception e) {
            logger.error("注册失败", e.getMessage());
            throw new XhException(CodeEnum.RUNTIME_EXCEPTION, e);
        }

        roleService.addDefaultUsrRole(user.getId());
    }

    public void updatePwdPrepare(String email, String code) {
        Check.notEmptyCheck(email, code);
        if (!AuthCodeHelper.verifyImgCode(code)) {
            throw new MsgException(CodeEnum.AUTH_CODE_ERR, "code is wrong");
        }

        User user = userService.findByEmail(email);
        if (user == null) {
            throw new MsgException(CodeEnum.USR_NOT_EXIST);
        }

        WebUtils.setSessionAttr(Final.Str.SESSION_UPDATE_PWD_USR_KEY, user);

        EmailAuthCode emailAuthCode = AuthCodeHelper.createEmailAuthCode(email);

        logger.debug("发送验证码邮件，内容为：" + emailAuthCode.getCode());
        EmailHelper.sendAuthCode(emailAuthCode.getCode(), email, user.getUsrName(), "修改密码");
    }

    public void updatePwd(String password, String code) {
        Check.notEmptyCheck(password, code);
        if (!AuthCodeHelper.verifyEmailCode(code)) {
            throw new MsgException(CodeEnum.AUTH_CODE_ERR, "code is wrong");
        }

        User user = WebUtils.getSessionAttr(Final.Str.SESSION_UPDATE_PWD_USR_KEY);

        user.setUsrPwd(PwdUtils.hashPassword(password));

        userService.updateById(user);
    }

    public UsrDto login(String loginName, String usrPwd) {
        logger.debug("loginName={}", loginName);

        loginName = StrUtils.trim(loginName);

        if (Check.isOneNull(loginName, usrPwd)) {
            throw new XhException(CodeEnum.NULL_EXCEPTION, "loginName or usrPwd or code is null");
        }

        Subject subject = SecurityUtils.getSubject();
        UsrDto currentUsr = (UsrDto) subject.getSession().getAttribute(Final.Str.SESSION_UER_KEY);

        if (Check.isAllNotNull(currentUsr) && loginName.equals(currentUsr.getUsrName())) {
            //该用户已经登录
            logger.debug("This user({}) is logged in", loginName);
            return currentUsr;
        }

        //登录名中存在@，则为邮箱账号
        User dbUsr = loginName.contains("@") ? userService.findByEmail(loginName) : userService.findByUsrName(loginName);

        if (Check.isNull(dbUsr)) {
            throw new MsgException(CodeEnum.USR_NOT_EXIST, "user not exist:loginName=" + loginName);
        }

        //验证密码
        if (!PwdUtils.passwordsMatch(usrPwd, dbUsr.getUsrPwd())) {
            throw new XhException(CodeEnum.PASSWORD_ERROR, "password is wrong");
        }
        return this.loginToShiro(dbUsr);
    }

    private UsrDto loginToShiro(User user) {
        logger.info("登录到系统：{}", user.getUsrName());
        String userName = user.getUsrName();
        String userPwd = user.getUsrPwd();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, userPwd);

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            logger.info("login failing:userName:{}, userPwd:{}", userName, userPwd);
            return null;
        }
        //构建dto
        UsrDto dtoUsr = new UsrDto(user);
        //注入session
        subject.getSession().setAttribute(Final.Str.SESSION_UER_KEY, dtoUsr);
        return dtoUsr;
    }

    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        subject.getSession().removeAttribute(Final.Str.SESSION_UER_KEY);
    }

    public boolean isLogin() {
        return SecurityUtils.getSubject().isAuthenticated();
    }
}
