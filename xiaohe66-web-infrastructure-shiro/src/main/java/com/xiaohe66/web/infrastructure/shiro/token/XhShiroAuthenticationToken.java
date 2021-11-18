package com.xiaohe66.web.infrastructure.shiro.token;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

import java.util.Set;


/**
 * @author xiaohe
 * @since 2021.10.29 18:05
 */
@RequiredArgsConstructor
@Getter
public class XhShiroAuthenticationToken implements AuthenticationToken {

    private static final Object PWD = new Object();

    private final Long accountId;
    private final Set<String> roles;
    private final Set<String> permissions;

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public Object getCredentials() {
        return PWD;
    }
}
