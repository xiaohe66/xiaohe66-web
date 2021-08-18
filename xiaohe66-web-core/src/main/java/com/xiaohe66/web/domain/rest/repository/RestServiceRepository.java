package com.xiaohe66.web.domain.rest.repository;

import com.xiaohe66.web.domain.rest.aggregate.RestAggregate;
import com.xiaohe66.web.domain.rest.value.RestId;
import com.xiaohe66.web.domain.rest.value.RestRepositoryDefinition;
import com.xiaohe66.web.integration.domain.Repository;
import com.xiaohe66.web.integration.value.Page;

/**
 * @author xiaohe
 * @since 2021.08.13 14:16
 */
public interface RestServiceRepository extends Repository<RestAggregate, RestId> {

    @Override
    void save(RestAggregate aggregate);

    @Override
    void removeById(RestId id);

    @Override
    RestAggregate findById(RestId id);

    Page<RestAggregate> page(Page<RestAggregate> page);

    Page<RestAggregate> page(RestAggregate query, Page<RestAggregate> page);

    RestRepositoryDefinition getRestRepositoryDefinition();

}
