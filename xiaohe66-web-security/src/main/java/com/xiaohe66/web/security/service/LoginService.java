package com.xiaohe66.web.security.service;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.ParamFinal;
import com.xiaohe66.web.base.exception.MsgException;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.PwdUtils;
import com.xiaohe66.web.base.util.StrUtils;
import com.xiaohe66.web.cache.Cache5Helper;
import com.xiaohe66.web.org.dto.UsrDto;
import com.xiaohe66.web.org.po.Usr;
import com.xiaohe66.web.org.service.UsrService;
import com.xiaohe66.web.security.auth.helper.AuthCodeHelper;
import com.xiaohe66.web.sys.helper.EmailHelper;
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
    private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);

    private static final String REGISTER_VERIFY = "http://xiaohe66.com/org/usr/verify/";

    /**
     * 锁
     */
    private final Object REGISTER_LOCK = new Object();

    @Autowired
    private UsrService usrService;

    @Autowired
    private UsrRoleService roleService;

    /**
     * 注册准备方法，发送注册邮件
     *
     * @param usr   Usr
     * @param code  当前操作的图片验证码
     */
    public void registerPrepare(Usr usr, String code){
        if(!AuthCodeHelper.verifyImgCode(code)){
            throw new XhException(CodeEnum.AUTH_CODE_ERR,"code is wrong");
        }

        String usrName = usr.getUsrName();
        String email = usr.getEmail();
        Check.notEmptyCheck(usrName,email);

        if(usrService.usrNameIsExist(usrName)){
            throw new XhException(CodeEnum.OBJ_ALREADY_EXIST,"usrName is exist");
        }

        if(usrService.emailIsExist(usr.getEmail())){
            throw new XhException(CodeEnum.OBJ_ALREADY_EXIST,"email is exist");
        }

        String token = PwdUtils.createToken();

        String link = REGISTER_VERIFY+token;

        LOG.debug("发送link邮件，内容为："+link);

        //发送邮件
        EmailHelper.sendLink(link,usr.getEmail(),usr.getUsrName(),"注册");

        Cache5Helper.put(token,usr);
    }

    @Transactional(rollbackFor = Exception.class)
    public void register(String token){
        Usr usr = Cache5Helper.get(token);

        if(usr == null){
            throw new MsgException(CodeEnum.TOKEN_TIME_OUT);
        }
        Cache5Helper.remove(token);

        usr.setUsrPwd(PwdUtils.getHashStr(usr.getUsrPwd()));
        try{
            usrService.add(usr,null);
        }catch (Exception e){
            LOG.error("注册失败",e.getMessage());
            throw new XhException(CodeEnum.RUNTIME_EXCEPTION,e);
        }

        roleService.addDefaultUsrRole(usr.getId());
    }

    public UsrDto login(String usrName, String usrPwd){
        LOG.debug("usrName="+usrName+",usrPwd="+usrPwd);

        usrName = StrUtils.trim(usrName);

        if(Check.isOneNull(usrName,usrPwd)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"usrName or usrPwd or code is null");
        }

        Subject subject = SecurityUtils.getSubject();
        UsrDto currentUsr = (UsrDto)subject.getSession().getAttribute(ParamFinal.SESSION_UER_KEY);

        if(Check.isAllNotNull(currentUsr) && usrName.equals(currentUsr.getUsrName())){
            //该用户已经登录
            LOG.info("This user("+usrName+") is logged in");
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
        LOG.info("登录到系统："+usr.getUsrName());
        String usrName = usr.getUsrName();
        String usrPwd = usr.getUsrPwd();
        UsernamePasswordToken token = new UsernamePasswordToken(usrName,usrPwd);

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            LOG.info("login failing:usrName="+usrName+",usrPwd="+usrPwd);
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
