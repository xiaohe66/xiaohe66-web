package com.xiaohe66.web.infrastructure.shiro.handler.extend;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.account.value.AccountName;
import com.xiaohe66.web.domain.sys.sec.entity.CurrentAccount;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.sys.sec.value.RoleName;
import com.xiaohe66.web.infrastructure.shiro.ShiroConst;
import com.xiaohe66.web.infrastructure.shiro.handler.ShiroFilterIsAccessAllowedHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

/**
 * @author xiaohe
 * @time 2019.12.09 11:07
 */
@Slf4j
@RequiredArgsConstructor
public class DevAutoLoginExtend implements ShiroFilterIsAccessAllowedHandler.Extend {

    private final SecurityService securityService;

    @Override
    public boolean before(HttpServletRequest request, HttpServletResponse response) {

        if (!securityService.isLogin()) {

            String uri = request.getRequestURI();

            if (!"/sec/login/wx".equals(uri)) {

                log.info("dev auto login");
                try {

                    AccountId id = new AccountId(ShiroConst.ADMIN_ACCOUNT_ID);
                    AccountName name = new AccountName(ShiroConst.ADMIN_ACCOUNT_NAME);
                    RoleName roleName = new RoleName(ShiroConst.ADMIN_ACCOUNT_NAME);

                    CurrentAccount currentAccount = CurrentAccount.builder()
                            .id(id)
                            .name(name)
                            .roleNames(Collections.singleton(roleName))
                            .permissionNames(Collections.emptySet())
                            .build();

                    securityService.login(currentAccount);
                    securityService.setCurrentAccount(currentAccount);

                    log.info("dev auto login success, account : {}", ShiroConst.ADMIN_ACCOUNT_NAME);

                } catch (Exception e) {

                    log.error("dev login error", e);
                }
            }
        }
        return true;
    }
}
