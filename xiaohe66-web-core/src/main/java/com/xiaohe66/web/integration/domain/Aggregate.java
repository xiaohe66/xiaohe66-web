package com.xiaohe66.web.integration.domain;

/**
 * 聚合根
 * <p>
 * 仓库操作的是聚合根
 *
 * @author xiaohe
 * @since 2021.08.10 10:08
 */
public interface Aggregate<T extends Aggregate<T, I>, I extends Id> extends Entity<I> {

    /**
     * 判断聚合值的属性是否不同(仅聚合根的字段), 对聚合根中的常量字段不进行判断。
     *
     * @return 返回 true 表示存在不同，返回 false 表示相同
     */
    default boolean hasDiffRootAttribute(T other) {
        return !hasSameRootAttribute(other);
    }

    /**
     * 判断聚合值的属性是否相同(仅聚合根的字段), 对聚合根中的常量字段不进行判断。
     *
     * @return 返回 true 表示相同，返回 false 表示不相同
     */
    boolean hasSameRootAttribute(T other);

}
