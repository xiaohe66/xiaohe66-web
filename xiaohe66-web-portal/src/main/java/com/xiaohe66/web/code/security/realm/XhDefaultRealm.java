package com.xiaohe66.web.code.security.realm;

import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.code.security.po.Role;
import com.xiaohe66.web.code.security.service.PermissionService;
import com.xiaohe66.web.code.security.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@Slf4j
public class XhDefaultRealm extends AuthorizingRealm {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {

        if (isAdmin(principals.getPrimaryPrincipal())) {
            log.debug("赋予管理员权限");
            return true;
        }

        return super.isPermitted(principals, permission);
    }

    @Override
    public boolean hasRole(PrincipalCollection principal, String roleIdentifier) {

        if (isAdmin(principal.getPrimaryPrincipal())) {
            log.debug("赋予管理员权限");
            return true;
        }

        return super.hasRole(principal, roleIdentifier);
    }

    @Override
    public boolean[] hasRoles(PrincipalCollection principal, List<String> roleIdentifiers) {

        if (isAdmin(principal.getPrimaryPrincipal())) {
            log.debug("赋予管理员权限");
            boolean[] result = new boolean[roleIdentifiers.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = true;
            }
            return result;
        }

        return super.hasRoles(principal, roleIdentifiers);
    }

    private boolean isAdmin(Object name) {
        return Final.User.ADMIN_USER_NAME.equals(name);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.debug("权限赋予:{}", principals);

        String userName = principals.toString();

        //获取用户角色
        List<Role> roleList = roleService.listRoleByUserName(userName);

        List<Integer> roleIdList = new ArrayList<>(roleList.size());
        Set<String> roleSet = new HashSet<>(roleList.size());

        for (Role role : roleList) {
            roleIdList.add(role.getId());
            roleSet.add(role.getRoleName());
        }

        log.debug("roles : {}", roleSet);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleSet);

        //获取用户权限
        Set<String> permissionSet = permissionService.listPermissionInRoleId(roleIdList);

        log.debug("permissions : {}", permissionSet);

        info.setStringPermissions(permissionSet);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), getName());
    }

}
