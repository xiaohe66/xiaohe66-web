package com.xiaohe66.web.application.sys.sec;

import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.domain.account.repository.AccountRepository;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.account.value.AccountName;
import com.xiaohe66.web.domain.account.value.AccountPassword;
import com.xiaohe66.web.domain.sys.sec.entity.CurrentAccount;
import com.xiaohe66.web.domain.sys.sec.repository.RoleRepository;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.sys.sec.value.RoleName;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
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

    CurrentAccount login(@NonNull AccountId accountId) {

        Account account = accountRepository.getById(accountId);
        if (account == null) {
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND_ACCOUNT);
        }

        return login(account);
    }

    CurrentAccount login(@NonNull Account account) {

        Set<RoleName> roles = account.roleIdsStream()
                .map(id -> roleRepository.getById(id).getRoleName())
                .collect(Collectors.toSet());

        // TODO : 权限
        CurrentAccount currentAccount = CurrentAccount.builder()
                .id(account.getId())
                .name(account.getName())
                .roleNames(roles)
                .permissionNames(Collections.emptySet())
                .build();

        securityService.login(currentAccount);
        securityService.setCurrentAccount(currentAccount);

        return currentAccount;
    }

    public CurrentAccount login(@NonNull String accountNameValue,
                                @NonNull String password) {

        AccountName accountName = new AccountName(accountNameValue);

        AccountPassword dbPassword = accountRepository.getPasswordByName(accountName);

        if (dbPassword == null) {
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND_ACCOUNT);
        }

        // 验证密码
        if (!dbPassword.verify(password)) {
            throw new BusinessException(ErrorCodeEnum.INVALID_TOKEN);
        }

        Account account = accountRepository.getByName(accountName);

        return login(account);
    }

}
