package com.xiaohe66.web.domain.account.aggregate;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.account.value.AccountName;
import com.xiaohe66.web.domain.account.value.AccountPassword;
import com.xiaohe66.web.domain.sys.sec.value.RoleId;
import com.xiaohe66.web.integration.domain.Aggregate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author xiaohe
 * @since 2021.08.10 15:07
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
public class Account implements Aggregate<Account, AccountId> {

    @NonNull
    private final AccountId id;

    @NonNull
    private AccountName name;

    @NonNull
    private AccountPassword password;

    @Getter(AccessLevel.NONE)
    @Builder.Default
    private Set<RoleId> roles = new HashSet<>();

    @Override
    public boolean hasSameRootAttribute(Account other) {
        return other != null &&
                Objects.equals(name, other.name) &&
                Objects.equals(password, other.password);
    }

    public void addRole(@NonNull RoleId roleId) {
        roles.add(roleId);
    }

    public void addRoleAll(@NonNull Collection<RoleId> roleIds) {
        roles.addAll(roleIds);
    }

    public void removeRole(@NonNull RoleId roleId) {
        roles.remove(roleId);
    }

    public boolean isSameRoles(@NonNull Account account) {
        return roles.equals(account.roles);
    }

    public boolean hasRole(@NonNull RoleId roleId) {
        return this.roles.contains(roleId);
    }

    public Stream<RoleId> roleIdsStream() {
        return roles.stream();
    }
}
