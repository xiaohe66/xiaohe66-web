package com.xiaohe66.web.security.role.service;

import com.xiaohe66.web.common.data.CheckUtils;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.StrEnum;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.StrUtils;
import com.xiaohe66.web.common.util.WebUtils;
import com.xiaohe66.web.org.usr.dto.UsrDto;
import com.xiaohe66.web.org.usr.po.Usr;
import com.xiaohe66.web.org.usr.service.UsrService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    private static final String HASH_ALGORITHM_NAME = "MD5";
    private static final int HASH_ITERATIONS = 1024;

    @Autowired
    private UsrService usrService;

    public LoginService(){
        System.out.println("loginService init");
    }


    @Transactional
    public UsrDto register(Usr usr, String code){
        if(CheckUtils.isNull(usr)){
            throw new XhException(CodeEnum.PARAM_ERR,"usr is null");
        }
        if(StrUtils.isOneEmpty(code)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"code is empty");
        }
        Session session = WebUtils.getSession();
        String sessionCode = (String) session.getAttribute(StrEnum.VALIDATE_CODE_STR_KEY.data());
        if(StrUtils.isOneEmpty(sessionCode)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"get code pls");
        }
        if(!sessionCode.equalsIgnoreCase(code)){
            throw new XhException(CodeEnum.PARAM_ERR,"code is wrong");
        }
        String usrName = usr.getUsrName();
        if(StrUtils.isOneEmpty(usrName,usr.getUsrName())){
            throw new XhException(CodeEnum.PARAM_ERR,"usrName or usrPwd is empty");
        }
        usr.setUsrPwd(this.toMD5(usr.getUsrPwd()));

        synchronized (LoginService.class){
            Usr dbUsr = usrService.findByUsrName(usrName);
            if(dbUsr != null){
                throw new XhException(CodeEnum.OBJ_ALREADY_EXIST,"usrName is exist");
            }
            usrService.add(usr,null);
        }
        usrService.addDefaultUsrRole(usr.getId());
        return this.loginToShiro(usr);
    }

    public UsrDto login(String usrName, String usrPwd){
        LOGGER.debug("usrName="+usrName+",usrPwd="+usrPwd);

        if(CheckUtils.isNull(usrName,usrPwd)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"usrName or usrPwd or code is null");
        }

        Subject subject = SecurityUtils.getSubject();
        UsrDto currentUsr = (UsrDto)subject.getSession().getAttribute(StrEnum.SESSION_UER_KEY.data());

        if(CheckUtils.isAllNotNull(currentUsr) && usrName.equals(currentUsr.getUsrName())){
            //该用户已经登录
            LOGGER.info("This user("+usrName+") is logged in");
            return currentUsr;
        }

        Usr dbUsr = usrService.findByUsrName(usrName);
        if(CheckUtils.isNull(dbUsr)){
            throw new XhException(CodeEnum.USR_NOT_EXIST,"usr not exist:usrName="+usrName);
        }
        //验证密码
        String pwdMD5 = toMD5(usrPwd);
        if(!pwdMD5.equals(dbUsr.getUsrPwd())){
            throw new XhException(CodeEnum.PASSWORD_ERROR,"password is wrong");
        }
        return this.loginToShiro(dbUsr);
    }

    private UsrDto loginToShiro(Usr usr){
        String usrName = usr.getUsrName();
        String usrPwd = usr.getUsrPwd();
        UsernamePasswordToken token = new UsernamePasswordToken(usrName,usrPwd);

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            LOGGER.info("login failing:usrName="+usrName+",usrPwd="+usrPwd);
            return null;
        }
        //构建dto
        UsrDto dtoUsr = new UsrDto(usr);
        //注入session
        subject.getSession().setAttribute(StrEnum.SESSION_UER_KEY.data(),dtoUsr);
        return dtoUsr;
    }

    private String toMD5(String pwd){
        SimpleHash obj = new SimpleHash(HASH_ALGORITHM_NAME, pwd, null, HASH_ITERATIONS);
        return obj.toBase64();
    }

    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        subject.getSession().removeAttribute(StrEnum.SESSION_UER_KEY.data());
    }

    public boolean isLogin(){
        return SecurityUtils.getSubject().isAuthenticated();
    }
}
