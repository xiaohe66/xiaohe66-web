package com.xiaohe66.web.integration.domain;

/**
 * @author xiaohe
 * @since 2021.08.10 09:47
 */
public interface Repository<T extends Aggregate<T, I>, I extends Id> {

    boolean isExist(I id);

    void save(T agg);

    T getById(I id);

    void removeById(I id);

}
