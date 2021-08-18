package com.xiaohe66.web.application;

import com.xiaohe66.web.domain.account.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author xiaohe
 * @since 2021.08.11 16:14
 */
@Service
@AllArgsConstructor
public class LoginService {

    private final AccountService userService;

}
