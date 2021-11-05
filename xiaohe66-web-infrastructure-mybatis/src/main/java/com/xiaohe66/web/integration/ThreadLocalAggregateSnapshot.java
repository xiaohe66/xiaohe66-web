package com.xiaohe66.web.integration;

import com.xiaohe66.web.integration.domain.Aggregate;
import com.xiaohe66.web.integration.domain.Id;

/**
 * @author xiaohe
 * @since 2021.11.04 11:20
 */
public class ThreadLocalAggregateSnapshot<A extends Aggregate<A, I>, I extends Id> implements AggregateSnapshot<A, I> {

    private final ThreadLocal<A> context = new ThreadLocal<>();

    @Override
    public void save(A agg) {
        context.set(agg);
    }

    @Override
    public A get(I id) {
        A agg = context.get();
        if (agg == null) {
            return null;
        }
        I aggId = agg.getId();

        if (!aggId.equals(id)) {

            context.remove();
            return null;
        }
        return agg;
    }

    @Override
    public void remove() {
        context.remove();
    }
}
