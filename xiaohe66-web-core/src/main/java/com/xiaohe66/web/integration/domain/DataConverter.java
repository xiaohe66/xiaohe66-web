package com.xiaohe66.web.integration.domain;

import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.love.value.LoverId;

import java.util.function.Function;

/**
 * @author xiaohe
 * @since 2021.11.17 15:07
 */
public interface DataConverter {

    default <T, V> V ifPresent(T object, Function<T, V> supplier) {
        return object != null ? supplier.apply(object) : null;
    }

    default Long asLongId(LongId longId) {
        return ifPresent(longId, LongId::getValue);
    }

    default Integer asIntId(IntId longId) {
        return ifPresent(longId, IntId::getValue);
    }

    default long asLongValue(LongValue longValue) {
        return longValue == null ? 0 : longValue.getValue();
    }

    default int asIntValue(IntValue intValue) {
        return intValue == null ? 0 : intValue.getValue();
    }

    default String asString(StringValue value) {
        return ifPresent(value, StringValue::getValue);
    }

    default AccountId newAccountId(Long accountId) {
        return ifPresent(accountId, AccountId::new);
    }

    default LoverId newLoverId(Long loverId) {
        return ifPresent(loverId, LoverId::new);
    }

    default String takeCreateTime(LongId id){
        return IdWorker.takeDate(id.getValue());
    }

}
