package com.xiaohe66.web.integration.domain;

/**
 * @author xiaohe
 * @since 2021.08.10 09:47
 */
public interface Repository<T extends Aggregate<I>, I extends Id> {

    void save(T aggregate);

    void removeById(I id);

    T findById(I id);

}
