package com.xiaohe66.web.integration.domain;

/**
 * 实体
 * <p>
 * 实体具有唯一性，一般为id，但不等于数据库中的表。
 * 实体不在意属性的变化，属性变化了（id不能变），这个实体还是原来的实体。
 *
 * @author xiaohe
 * @since 2021.08.10 10:07
 */
public interface Entity<I extends Id> extends Identifiable<I> {

    default boolean isSameIdentity(Entity<I> other) {

        I id = getId();

        return id == null ? other == null : id.equals(other.getId());
    }

}
