package com.xiaohe66.web.infrastructure.mybatis.account.convert;

import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.account.value.AccountName;
import com.xiaohe66.web.domain.account.value.AccountPassword;
import com.xiaohe66.web.infrastructure.mybatis.account.model.AccountDo;
import com.xiaohe66.web.infrastructure.mybatis.sys.sec.model.AccountRoleDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohe
 * @since 2021.08.12 11:02
 */
@Mapper(componentModel = "spring")
public interface AccountDoConverter extends DoConverter<Account, AccountDo> {

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


    default List<AccountRoleDo> toAccountRoleList(Account account) {

        Long accountIdValue = account.getId().getValue();

        return account.roleIdsStream()
                .map(roleId -> {
                    AccountRoleDo accountRoleDo = new AccountRoleDo();
                    accountRoleDo.setId(IdWorker.genId());
                    accountRoleDo.setAccountId(accountIdValue);
                    accountRoleDo.setRoleId(roleId.getValue());
                    return accountRoleDo;
                })
                .collect(Collectors.toList());
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
