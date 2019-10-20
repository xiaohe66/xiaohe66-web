package com.xiaohe66.web.base.base;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author xiaohe
 * @time 2019.10.11 18:14
 */
public class XhPage<T extends BasePo> extends Page<T> {

    public static final int DEFAULT_PAGE_SIZE = 20;

    private static final int MAX_PAGE_SIZE = 30;

    public XhPage() {
        super(1, DEFAULT_PAGE_SIZE, true);
    }

    public XhPage(long pageSize) {
        super(1, pageSize > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : pageSize, true);
    }

    public XhPage(long pageSize, long pageNo) {
        super(pageNo, pageSize > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : pageSize, true);
    }

    @Override
    public Page<T> setSize(long size) {
        return super.setSize(getSafetyPageSize(size));
    }

    public long getSafetyPageSize(long size) {
        return size > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : size;
    }
}
