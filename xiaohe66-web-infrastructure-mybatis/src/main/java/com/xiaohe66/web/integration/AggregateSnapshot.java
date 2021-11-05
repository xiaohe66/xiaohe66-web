package com.xiaohe66.web.integration;

import com.xiaohe66.web.integration.domain.Aggregate;
import com.xiaohe66.web.integration.domain.Id;

/**
 * @author xiaohe
 * @since 2021.11.04 11:17
 */
public interface AggregateSnapshot<A extends Aggregate<A, I>, I extends Id> {

    void save(A agg);

    A get(I id);

    void remove();
}
