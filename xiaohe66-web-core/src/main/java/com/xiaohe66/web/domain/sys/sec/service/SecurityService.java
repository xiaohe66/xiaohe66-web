package com.xiaohe66.web.domain.sys.sec.service;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.sys.sec.entity.CurrentAccount;

/**
 * @author xiaohe
 * @since 2021.10.28 16:15
 */
public interface SecurityService {

    String CURRENT_USER_KEY = "session_current_user";

    void login(CurrentAccount currentAccount);

    void logout();

    boolean isLogin();

    String getSessionId();

    void setCurrentAccount(CurrentAccount account);

    CurrentAccount getCurrentAccount();

    AccountId getCurrentAccountId();

    void setAttribute(Object key, Object value);

    <T> T getAttribute(Object key);

    boolean isAdmin();

    boolean hasRole(String role);

    boolean hasRoles(String... roles);

    boolean hasPermission(String permission);

    boolean hasPermissions(String... permission);

    /**
     * 是否有创建人权限（检查当前用户是否为指定值，管理员默认拥有全部权限）
     */
    boolean hasCreatorPermission(AccountId createId);

    void checkLogin();

    void checkRole(String role);

    void checkRoles(String... roles);

    void checkPermission(String permission);

    void checkPermission(String... permission);

    /**
     * 检查是否有创建人权限（检查当前用户是否为指定值，管理员默认拥有全部权限）
     */
    void checkCreatorPermission(AccountId createId);

}
