package com.xiaohe66.web.integration;

import com.xiaohe66.web.integration.domain.Aggregate;
import com.xiaohe66.web.integration.domain.IntId;
import com.xiaohe66.web.integration.domain.LongId;
import com.xiaohe66.web.integration.domain.StringValue;

import java.util.function.Function;

/**
 * @author xiaohe
 * @since 2021.08.12 12:00
 */
public interface DataConverter<A extends Aggregate<A, ?>, D extends IDo> {

    A toAgg(D doObj);

    D toDo(A agg);

    A copyAgg(A account);

    default <T, V> V ifPresent(T object, Function<T, V> supplier) {
        return object != null ? supplier.apply(object) : null;
    }

    default Long asLongId(LongId longId) {
        return ifPresent(longId, LongId::getValue);
    }

    default Integer asIntId(IntId longId) {
        return ifPresent(longId, IntId::getValue);
    }

    default String asString(StringValue value) {
        return ifPresent(value, StringValue::getValue);
    }

}
