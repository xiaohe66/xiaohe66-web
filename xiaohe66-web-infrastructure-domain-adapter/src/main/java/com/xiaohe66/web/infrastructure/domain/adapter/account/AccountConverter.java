package com.xiaohe66.web.infrastructure.domain.adapter.account;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.account.value.AccountName;
import com.xiaohe66.web.domain.account.value.AccountPassword;
import com.xiaohe66.web.infrastructure.domain.adapter.DataConverter;

/**
 * @author xiaohe
 * @since 2022.03.30 11:42
 */
public interface AccountConverter extends DataConverter {

    //@Mapping(source = "password", target = "passwordSalt")
    //@Mapping(source = "password", target = "passwordHash")
    //Account toEntity(AccountDo accountDo);

    default Long asId(AccountId id) {
        return id == null ? null : id.getValue();
    }

    default String asName(AccountName name) {
        return ifPresent(name, AccountName::getValue);
    }

    default AccountName newName(String name) {
        return ifPresent(name, AccountName::new);
    }

    default AccountPassword newPassword(String password) {
        return ifPresent(password, AccountPassword::new);
    }

    default String asPassword(AccountPassword password) {
        return ifPresent(password, AccountPassword::getValue);
    }


    /*default AccountPasswordSalt newSalt(String password) {
        return new AccountPasswordSalt(PasswordUtils.getSalt(password));
    }

    default AccountPasswordHash newHash(String password) {
        return new AccountPasswordHash(PasswordUtils.getHash(password));
    }

    @AfterMapping
    default void asPassword(Account source, @MappingTarget AccountDo target) {
        String pwd = source.getPasswordSalt().getValue() + source.getPasswordHash().getValue();
        target.setPassword(pwd);
    }*/
}
