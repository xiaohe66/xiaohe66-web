package com.xiaohe66.web.domain.rest.aggregate;

import com.xiaohe66.web.domain.rest.value.RestId;
import com.xiaohe66.web.integration.domain.Aggregate;
import com.xiaohe66.web.integration.domain.Entity;
import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.08.13 14:17
 */
@Data
public class RestAggregate implements Aggregate<RestId> {

    private RestId id;
    private Entity<RestId> entity;

    @Override
    public RestId getId() {
        return id;
    }
}
