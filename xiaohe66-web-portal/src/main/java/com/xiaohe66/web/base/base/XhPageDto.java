package com.xiaohe66.web.base.base;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author xiaohe
 * @time 2019.10.20 21:51
 */
public class XhPageDto<D extends BaseDto> extends Page<D> {

    private long pages;

    @Override
    public IPage<D> setPages(long pages) {
        this.pages = pages;
        return this;
    }


    @Override
    public long getPages() {
        if(pages == 0){
            pages = super.getPages();
        }
        return pages;
    }
}
