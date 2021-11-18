package com.xiaohe66.web.integration.domain;

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

}
