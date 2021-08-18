package com.xiaohe66.web.domain.account.service;

import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 账号领域服务
 *
 * @author xiaohe
 * @since 2021.08.10 18:35
 */
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository userRepository;

    public void save(Account account) {

        userRepository.save(account);
    }

}
