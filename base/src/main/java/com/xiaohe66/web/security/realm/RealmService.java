package com.xiaohe66.web.security.realm;

import com.xiaohe66.web.org.usr.service.UsrService;
import com.xiaohe66.web.security.role.service.FuncService;
import com.xiaohe66.web.security.role.service.RoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
public class RealmService extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(RealmService.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private FuncService funcService;

    @Autowired
    private UsrService usrService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LOGGER.debug("权限赋予:"+principals);

        Long usrId = (Long) principals.getPrimaryPrincipal();

        //获取用户角色
        Set<String> roleSet = roleService.findRoleNameByUsrId(usrId);

        LOGGER.debug("roles:"+roleSet.toString());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleSet);

        //获取用户权限
        Set<String> funcSet = funcService.findFuncNameByUsrId(usrId);

        LOGGER.debug("func:"+funcSet.toString());

        info.setStringPermissions(funcSet);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
        return new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),getName());
    }
}
