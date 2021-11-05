package com.xiaohe66.web.domain.sys.sec.service;

import com.xiaohe66.web.domain.sys.sec.entity.CurrentAccount;
import com.xiaohe66.web.domain.sys.sec.ex.LoginException;

/**
 * @author xiaohe
 * @since 2021.10.28 16:15
 */
public interface SecurityService {

    void login(CurrentAccount currentAccount) throws LoginException;

    void logout();

    boolean isLogin();

    String getSessionId();

    void setAttribute(Object key, Object value);

    <T> T getAttribute(Object key);

    boolean hasRole(String role);

    boolean hasRoles(String... roles);

    boolean hasPermission(String permission);

    boolean hasPermission(String... permission);

}
