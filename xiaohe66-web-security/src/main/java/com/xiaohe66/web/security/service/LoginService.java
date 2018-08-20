package com.xiaohe66.web.security.service;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.ParamFinal;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.PwdUtils;
import com.xiaohe66.web.base.util.StrUtils;
import com.xiaohe66.web.org.dto.UsrDto;
import com.xiaohe66.web.org.po.Usr;
import com.xiaohe66.web.org.service.UsrService;
import com.xiaohe66.web.security.auth.AuthCodeHelper;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    /**
     * 锁
     */
    private final Object REGISTER_LOCK = new Object();

    @Autowired
    private UsrService usrService;

    @Autowired
    private UsrRoleService roleService;

    @Transactional(rollbackFor = Exception.class)
    public UsrDto register(Usr usr, String code){
        if(Check.isNull(usr)){
            throw new XhException(CodeEnum.PARAM_ERR,"usr is null");
        }
        if(StrUtils.isOneEmpty(code)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"code is empty");
        }

        if(!AuthCodeHelper.verify(code)){
            throw new XhException(CodeEnum.AUTH_CODE_ERR,"code is wrong");
        }

        String usrName = usr.getUsrName();
        if(StrUtils.isOneEmpty(usrName,usr.getUsrName())){
            throw new XhException(CodeEnum.PARAM_ERR,"usrName or usrPwd is empty");
        }
        usr.setUsrPwd(PwdUtils.getHashStr(usr.getUsrPwd()));

        LOGGER.info("注册："+usr.getUsrName());
        synchronized (REGISTER_LOCK){
            Usr dbUsr = usrService.findByUsrName(usrName);
            if(dbUsr != null){
                throw new XhException(CodeEnum.OBJ_ALREADY_EXIST,"usrName is exist");
            }
            usrService.add(usr,null);
        }
        roleService.addDefaultUsrRole(usr.getId());
        return this.loginToShiro(usr);
    }

    public UsrDto login(String usrName, String usrPwd){
        LOGGER.debug("usrName="+usrName+",usrPwd="+usrPwd);

        usrName = StrUtils.trim(usrName);

        if(Check.isOneNull(usrName,usrPwd)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"usrName or usrPwd or code is null");
        }

        Subject subject = SecurityUtils.getSubject();
        UsrDto currentUsr = (UsrDto)subject.getSession().getAttribute(ParamFinal.SESSION_UER_KEY);

        if(Check.isAllNotNull(currentUsr) && usrName.equals(currentUsr.getUsrName())){
            //该用户已经登录
            LOGGER.info("This user("+usrName+") is logged in");
            return currentUsr;
        }

        Usr dbUsr = usrService.findByUsrName(usrName);
        if(Check.isNull(dbUsr)){
            throw new XhException(CodeEnum.USR_NOT_EXIST,"usr not exist:usrName="+usrName);
        }
        //验证密码
        String pwdMD5 = PwdUtils.getHashStr(usrPwd);
        if(!pwdMD5.equals(dbUsr.getUsrPwd())){
            throw new XhException(CodeEnum.PASSWORD_ERROR,"password is wrong");
        }
        return this.loginToShiro(dbUsr);
    }

    private UsrDto loginToShiro(Usr usr){
        LOGGER.info("登录到系统："+usr.getUsrName());
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
        subject.getSession().setAttribute(ParamFinal.SESSION_UER_KEY,dtoUsr);
        return dtoUsr;
    }

    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        subject.getSession().removeAttribute(ParamFinal.SESSION_UER_KEY);
    }

    public boolean isLogin(){
        return SecurityUtils.getSubject().isAuthenticated();
    }
}
