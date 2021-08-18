package com.xiaohe66.web.infrastructure.mybatis;

import com.xiaohe66.web.domain.rest.aggregate.RestAggregate;
import com.xiaohe66.web.domain.rest.repository.RestServiceRepository;
import com.xiaohe66.web.domain.rest.value.RestId;
import com.xiaohe66.web.domain.rest.value.RestRepositoryDefinition;
import com.xiaohe66.web.integration.value.Page;

/**
 * @author xiaohe
 * @since 2021.08.18 10:45
 */
public class RestServiceRepositoryImpl implements RestServiceRepository {

    @Override
    public void save(RestAggregate aggregate) {

    }

    @Override
    public void removeById(RestId id) {

    }

    @Override
    public RestAggregate findById(RestId id) {
        return null;
    }

    @Override
    public Page<RestAggregate> page(Page<RestAggregate> page) {
        return null;
    }

    @Override
    public Page<RestAggregate> page(RestAggregate query, Page<RestAggregate> page) {
        return null;
    }

    @Override
    public RestRepositoryDefinition getRestRepositoryDefinition() {
        return null;
    }
}
