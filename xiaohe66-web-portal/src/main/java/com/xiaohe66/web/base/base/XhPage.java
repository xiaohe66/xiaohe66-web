package com.xiaohe66.web.base.base;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author xiaohe
 * @time 2019.10.11 18:14
 */
public class XhPage<T extends BasePo> extends Page<T> {

    public XhPage() {
        super(1, 20, false);
    }

    public XhPage(long current, long size) {
        super(current, size, false);
    }

    public XhPage(long current, long size, boolean isSearchCount) {
        super(current, size, isSearchCount);
    }

}
