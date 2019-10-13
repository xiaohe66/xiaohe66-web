package com.xiaohe66.web.base.base;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author xiaohe
 * @time 2019.10.11 18:14
 */
public class XhPage<T extends BasePo> extends Page<T> {

    public static final int DEFAULT_PAGE_SIZE = 20;

    public XhPage() {
        super(1, DEFAULT_PAGE_SIZE, false);
    }

    public XhPage(long pageSize) {
        super(1, pageSize, false);
    }

    public XhPage(long size, long pageNo) {
        super(pageNo, size, false);
    }

    public XhPage(long current, long size, boolean isSearchCount) {
        super(current, size, isSearchCount);
    }

}
