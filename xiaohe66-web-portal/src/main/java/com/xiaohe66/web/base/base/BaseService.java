package com.xiaohe66.web.base.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 基础service，包含基础增删改查方法
 *
 * @author xiaohe
 * @time 17-10-28 028
 */
public interface BaseService<P extends BasePo> extends IService<P> {

    IPage<P> page(long pageSize);

    IPage<P> page(long pageSize, long pageNo);

    IPage<P> page(long pageSize, P po);

    IPage<P> pageDefault(Long pageSize, Long pageNo, P po);

    QueryWrapper<P> createPageDefaultQueryWrapper(P po);
}
