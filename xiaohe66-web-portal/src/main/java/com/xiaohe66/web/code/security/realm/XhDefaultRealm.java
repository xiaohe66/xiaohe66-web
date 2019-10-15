package com.xiaohe66.web.code.security.realm;

import com.xiaohe66.web.code.security.po.Role;
import com.xiaohe66.web.code.security.service.PermissionService;
import com.xiaohe66.web.code.security.service.RoleService;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
public class XhDefaultRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(XhDefaultRealm.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.debug("权限赋予:{}", principals);

        String userName = principals.toString();

        //获取用户角色
        List<Role> roleList = roleService.listRoleByUserName(userName);

        List<Integer> roleIdList = new ArrayList<>(roleList.size());
        Set<String> roleSet = new HashSet<>(roleList.size());

        for (Role role : roleList) {
            roleIdList.add(role.getId());
            roleSet.add(role.getRoleName());
        }

        logger.debug("roles : {}", roleSet);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleSet);

        //获取用户权限
        Set<String> permissionSet = permissionService.listPermissionInRoleId(roleIdList);

        logger.debug("permissions : {}", permissionSet);

        info.setStringPermissions(permissionSet);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), getName());
    }
}
