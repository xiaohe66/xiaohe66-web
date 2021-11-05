package com.xiaohe66.web.domain.account.service;

import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.domain.account.repository.AccountRepository;
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

        List<Role> defaultRoles = roleRepository.listDefaultRole();
        //account.setRoles(defaultRoles);

        accountRepository.save(account);
    }

}
