package com.xiaohe66.web.application.sys.sec;

import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.domain.account.repository.AccountRepository;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.account.value.AccountName;
import com.xiaohe66.web.domain.account.value.AccountPassword;
import com.xiaohe66.web.domain.sys.sec.entity.CurrentAccount;
import com.xiaohe66.web.domain.sys.sec.ex.LoginException;
import com.xiaohe66.web.domain.sys.sec.repository.RoleRepository;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.sys.sec.service.SessionManager;
import com.xiaohe66.web.domain.sys.sec.value.RoleName;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xiaohe
 * @since 2021.08.11 16:14
 */
@Service
@AllArgsConstructor
public class LoginService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final SecurityService securityService;

    private final SessionManager sessionManager;
    /*
    TODO : 登录的返回值需要改变成 R<?> 吗
     */

    CurrentAccount login(@NonNull AccountId accountId) throws LoginException {

        Account account = accountRepository.getById(accountId);
        if (account == null) {
            throw new LoginException("login fail, not found account");
        }

        return login(account);
    }

    CurrentAccount login(@NonNull Account account) throws LoginException {

        Set<RoleName> roles = account.roleIdsStream()
                .map(id -> roleRepository.getById(id).getRoleName())
                .collect(Collectors.toSet());

        // TODO : 权限
        CurrentAccount currentAccount = CurrentAccount.builder()
                .id(account.getId())
                .name(account.getName())
                .roleNames(roles)
                .roleNames(Collections.emptySet())
                .build();

        securityService.login(currentAccount);

        sessionManager.setCurrentAccount(currentAccount);

        return currentAccount;
    }

    public CurrentAccount login(@NonNull String accountNameValue,
                                @NonNull String password) throws LoginException {

        AccountName accountName = new AccountName(accountNameValue);

        AccountPassword dbPassword = accountRepository.getPasswordByName(accountName);

        if (dbPassword == null) {
            throw new LoginException("account not found : " + accountNameValue);
        }

        // 验证密码
        if (!dbPassword.verify(password)) {

            throw new LoginException("password is error : " + accountNameValue);
        }

        Account account = accountRepository.getByName(accountName);

        return login(account);
    }

}
