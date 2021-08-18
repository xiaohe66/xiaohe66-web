package com.xiaohe66.web.integration.value;

import lombok.Data;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.08.13 14:27
 */
@Data
public class Page<T> {

    private long pages;
    private long total;
    private long pageSize;
    private long pageNo;
    private List<T> records;

}
