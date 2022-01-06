package com.xiaohe66.web.domain.account.service;

import com.xiaohe66.common.util.ex.BusinessException;
import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.domain.account.repository.AccountRepository;
import com.xiaohe66.web.domain.account.value.AccountPassword;
import com.xiaohe66.web.domain.sys.sec.aggregate.Role;
import com.xiaohe66.web.domain.sys.sec.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账号领域服务
 *
 * @author xiaohe
 * @since 2021.08.10 18:35
 */
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    public void register(Account account) {

        AccountPassword password = accountRepository.getPasswordByName(account.getName());
        if (password != null) {
            throw new BusinessException("account name is exist");
        }

        List<Role> defaultRoles = roleRepository.listDefaultRole();

        for (Role role : defaultRoles) {
            account.addRole(role.getId());
        }

        accountRepository.save(account);
    }

}
