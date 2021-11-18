package com.xiaohe66.web.infrastructure.shiro;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.sys.sec.entity.CurrentAccount;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.sys.sec.value.PermissionName;
import com.xiaohe66.web.domain.sys.sec.value.RoleName;
import com.xiaohe66.web.infrastructure.shiro.token.XhShiroAuthenticationToken;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author xiaohe
 * @since 2021.10.29 18:01
 */
@Slf4j
@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public void login(CurrentAccount context) {

        Long accountId = context.getId().getValue();

        Set<String> roles = context.getRoleNames().stream()
                .map(RoleName::getValue)
                .collect(Collectors.toSet());

        Set<String> permissions = context.getPermissionNames().stream()
                .map(PermissionName::getValue)
                .collect(Collectors.toSet());

        XhShiroAuthenticationToken token = new XhShiroAuthenticationToken(accountId, roles, permissions);

        try {
            getSubject().login(token);
            // shiro 的登录完成后，isAuthenticated() 还是会返回 false
            //return subject.isAuthenticated();

        } catch (AuthenticationException e) {

            log.error("shiro login fail : {}({})",
                    context.getName().getValue(),
                    context.getId().getValue(),
                    e);

            throw new BusinessException(ErrorCodeEnum.ERROR);
        }

    }

    @Override
    public void logout() {

        getSubject().logout();
    }

    @Override
    public boolean isLogin() {
        return getSubject().isAuthenticated();
    }

    @Override
    public String getSessionId() {
        return getSubject().getSession().getId().toString();
    }

    @Override
    public void setCurrentAccount(CurrentAccount account) {
        setAttribute(CURRENT_USER_KEY, account);
    }

    @Override
    public CurrentAccount getCurrentAccount() {

        if (!isLogin()) {
            throw new BusinessException(ErrorCodeEnum.NOT_LOGIN);
        }
        return getAttribute(CURRENT_USER_KEY);
    }

    @Override
    public AccountId getCurrentAccountId() {
        return getCurrentAccount().getId();
    }

    @Override
    public void setAttribute(Object key, Object value) {

        Session session = getSession();

        session.setAttribute(key, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAttribute(Object key) {

        Session session = getSession();
        return (T) session.getAttribute(key);
    }

    @Override
    public boolean isAdmin() {
        return getSubject().hasRole(ShiroConst.ADMIN_ROLE_NAME);
    }

    @Override
    public boolean hasRole(String role) {
        return getSubject().hasRole(role);
    }

    @Override
    public boolean hasRoles(String... roles) {
        List<String> roleList = Arrays.asList(roles);
        return getSubject().hasAllRoles(roleList);
    }

    @Override
    public boolean hasPermission(String permission) {
        return getSubject().isPermitted(permission);
    }

    @Override
    public boolean hasPermissions(String... permission) {
        return getSubject().isPermittedAll(permission);
    }

    /**
     * 是否有创建人权限（检查当前用户是否为指定值，管理员默认拥有全部权限）
     */
    @Override
    public boolean hasCreatorPermission(AccountId createId) {

        if (createId == null) {
            return false;
        }

        if (isAdmin()) {
            return true;
        }

        AccountId accountId = getCurrentAccountId();

        return accountId.equals(createId);
    }

    @Override
    public void checkLogin() {
        if (!isLogin()) {
            throw new BusinessException(ErrorCodeEnum.NOT_LOGIN);
        }
    }

    @Override
    public void checkRole(String role) {
        if (!hasRole(role)) {
            throw new BusinessException(ErrorCodeEnum.NOT_FUNCTION_PERMISSION);
        }
    }

    @Override
    public void checkRoles(String... roles) {
        if (!hasRoles(roles)) {
            throw new BusinessException(ErrorCodeEnum.NOT_FUNCTION_PERMISSION);
        }
    }

    @Override
    public void checkPermission(String permission) {
        if (!hasPermission(permission)) {
            throw new BusinessException(ErrorCodeEnum.NOT_FUNCTION_PERMISSION);
        }
    }

    @Override
    public void checkPermission(String... permission) {
        if (!hasPermissions(permission)) {
            throw new BusinessException(ErrorCodeEnum.NOT_FUNCTION_PERMISSION);
        }
    }

    /**
     * 检查是否有创建人权限（检查当前用户是否为指定值，管理员默认拥有全部权限）
     */
    @Override
    public void checkCreatorPermission(AccountId createId) {
        if (!hasCreatorPermission(createId)) {
            throw new BusinessException(ErrorCodeEnum.NOT_DATA_PERMISSION);
        }
    }

    private Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    private Session getSession() {
        return getSubject().getSession();
    }
}
