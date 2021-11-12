package com.xiaohe66.web.domain.sys.sec.service;

import com.xiaohe66.web.domain.sys.sec.entity.CurrentAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author xiaohe
 * @since 2021.11.12 15:30
 */
@Component
@RequiredArgsConstructor
public class SessionManager {

    public static final String CURRENT_USER_KEY = "session_current_user";

    private final SecurityService securityService;

    public String getSessionId() {
        return securityService.getSessionId();
    }

    public void setAttribute(Object key, Object value) {
        securityService.setAttribute(key, value);
    }

    public <T> T getAttribute(Object key) {
        return securityService.getAttribute(key);
    }

    public void setCurrentAccount(CurrentAccount account) {
        securityService.setAttribute(CURRENT_USER_KEY, account);
    }

    public CurrentAccount getCurrentAccount() {
        return securityService.getAttribute(CURRENT_USER_KEY);
    }
}
