package com.xiaohe66.web.application;

import com.xiaohe66.web.integration.domain.Paging;
import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.11.30 14:40
 */
@Data
public class PagingBo {

    private Long before;
    private Integer size;

    public Paging toPaging() {
        return new Paging(before, size);
    }

}
