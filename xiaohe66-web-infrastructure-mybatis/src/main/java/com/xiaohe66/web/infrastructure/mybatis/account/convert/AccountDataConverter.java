package com.xiaohe66.web.infrastructure.mybatis.account.convert;

import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.account.value.AccountName;
import com.xiaohe66.web.infrastructure.mybatis.account.model.AccountDo;
import com.xiaohe66.web.integration.domain.DataConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.08.12 11:02
 */
@Mapper(componentModel = "spring")
public interface AccountDataConverter extends DataConverter {

    Account toEntity(AccountDo accountDo);

    AccountDo toDo(Account account);

    default Long asId(AccountId id) {
        return id == null ? null : id.getValue();
    }

    default String asName(AccountName name) {
        return name.getValue();
    }

    default AccountName newName(String name) {
        return new AccountName(name);
    }

    default AccountId newId(long id) {
        return new AccountId(id);
    }

}
