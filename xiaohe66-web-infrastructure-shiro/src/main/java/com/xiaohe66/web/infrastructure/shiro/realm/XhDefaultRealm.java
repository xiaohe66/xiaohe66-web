package com.xiaohe66.web.infrastructure.shiro.realm;

import com.xiaohe66.web.infrastructure.shiro.ShiroConst;
import com.xiaohe66.web.infrastructure.shiro.token.XhShiroAuthenticationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Arrays;
import java.util.List;

/**
 * @author xiaohe66
 * @since 2017.10.28
 */
@Slf4j
@RequiredArgsConstructor
public class XhDefaultRealm extends AuthorizingRealm {

    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {

        if (isAdmin(principals)) {
            return true;
        }

        return super.isPermitted(principals, permission);
    }

    @Override
    public boolean hasRole(PrincipalCollection principal, String roleIdentifier) {

        if (isAdmin(principal)) {
            return true;
        }

        return super.hasRole(principal, roleIdentifier);
    }

    @Override
    public boolean[] hasRoles(PrincipalCollection principal, List<String> roleIdentifiers) {

        if (isAdmin(principal)) {

            boolean[] result = new boolean[roleIdentifiers.size()];
            Arrays.fill(result, true);
            return result;
        }

        return super.hasRoles(principal, roleIdentifiers);
    }

    private boolean isAdmin(PrincipalCollection principal) {

        boolean ret = super.hasRole(principal, ShiroConst.ADMIN_ROLE_NAME);
        log.debug("give admin permission");

        return ret;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        XhShiroAuthenticationToken account = (XhShiroAuthenticationToken) principals.getPrimaryPrincipal();

        log.debug("give auth : {}, roles : {}, permissions : {}", account.getAccountId(), account.getRoles(), account.getPermissions());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(account.getRoles());

        info.setStringPermissions(account.getPermissions());

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {

        // 正常来说, 这里的实现是返回真正的认证令牌（密码）。但是这个项目将令牌的验证放在了外面了。

        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), getName());
    }

    /**
     * 返回该 realm 对应的 token
     * <p>若登录时不匹配该 token, 该后续在 {@link AuthorizingRealm#supports} 方法中会
     * 抛出 {@link org.apache.shiro.authc.pam.UnsupportedTokenException} 异常
     */
    @Override
    public Class<XhShiroAuthenticationToken> getAuthenticationTokenClass() {
        return XhShiroAuthenticationToken.class;
    }
}
