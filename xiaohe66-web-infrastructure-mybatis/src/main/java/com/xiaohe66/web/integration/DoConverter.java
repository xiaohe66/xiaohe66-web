package com.xiaohe66.web.integration;

import com.xiaohe66.web.integration.domain.Aggregate;
import com.xiaohe66.web.integration.domain.DataConverter;

/**
 * @author xiaohe
 * @since 2021.08.12 12:00
 */
public interface DoConverter<A extends Aggregate<A, ?>, D extends IDo> extends DataConverter {

    A toAgg(D doObj);

    D toDo(A agg);

    A copyAgg(A account);

}
