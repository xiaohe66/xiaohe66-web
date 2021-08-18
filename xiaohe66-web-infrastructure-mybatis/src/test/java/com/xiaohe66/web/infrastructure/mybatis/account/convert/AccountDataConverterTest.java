package com.xiaohe66.web.infrastructure.mybatis.account.convert;

import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.account.value.AccountName;
import com.xiaohe66.web.infrastructure.mybatis.account.model.AccountDo;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class AccountDataConverterTest {

    private static final Logger log = LoggerFactory.getLogger(AccountDataConverterTest.class);

    private AccountDataConverter converter;

    @Before
    public void name() {

        // 初始化一个容器用于扫描
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan(AccountDataConverter.class.getPackage().getName());
        context.refresh();
        converter = context.getBean(AccountDataConverter.class);
    }

    @Test
    public void test() {

        long id = 100;
        String name = "test name";

        Account account = new Account(new AccountId(id));
        account.setName(new AccountName(name));

        AccountDo accountDo = converter.toDo(account);

        AccountDo correct = new AccountDo();
        correct.setId(id);
        correct.setName(name);

        assertEquals(correct, accountDo);

        Account result = converter.toEntity(accountDo);

        assertEquals(account, result);

        log.info("result : {}", result);

    }
}