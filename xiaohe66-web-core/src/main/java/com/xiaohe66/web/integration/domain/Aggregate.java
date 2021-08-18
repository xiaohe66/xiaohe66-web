package com.xiaohe66.web.integration.domain;

/**
 * 聚合根
 * <p>
 * 仓库操作的是聚合根
 *
 * @author xiaohe
 * @since 2021.08.10 10:08
 */
public interface Aggregate<I extends Id> extends Entity<I> {

}
