package com.xiaohe66.web.domain.sys.sec.entity;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.account.value.AccountName;
import com.xiaohe66.web.domain.sys.sec.value.PermissionName;
import com.xiaohe66.web.domain.sys.sec.value.RoleName;
import com.xiaohe66.web.integration.domain.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Collections;
import java.util.Set;

/**
 * @author xiaohe
 * @since 2021.10.28 16:11
 */
@ToString
@Getter
@Builder
@AllArgsConstructor
public class CurrentAccount implements Entity<AccountId> {

    @NonNull
    private final AccountId id;

    @NonNull
    private final AccountName name;

    @Builder.Default
    private Set<RoleName> roleNames = Collections.emptySet();

    @Builder.Default
    private Set<PermissionName> permissionNames = Collections.emptySet();


    @Override
    public AccountId getId() {
        return id;
    }
}
