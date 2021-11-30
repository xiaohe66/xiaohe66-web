package com.xiaohe66.web.integration.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.11.30 14:43
 */
@ToString
@EqualsAndHashCode
@Getter
public class Paging {

    private final long before;
    private final int size;

    public Paging(Long before, Integer size) {

        this.before = before == null || before < 0 ? 0 : before;

        if (size == null) {
            this.size = 10;

        } else if (size < 5) {
            this.size = 5;

        } else if (size > 50) {
            this.size = 50;

        } else {
            this.size = size;
        }
    }

    public String toLimit() {
        return "limit " + before + "," + size;
    }
}
