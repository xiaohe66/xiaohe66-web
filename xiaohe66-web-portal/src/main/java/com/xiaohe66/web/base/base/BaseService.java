package com.xiaohe66.web.base.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 基础service，包含基础增删改查方法
 *
 * @author xiaohe
 * @time 17-10-28 028
 */
public interface BaseService<T extends BasePo> extends IService<T> {

    IPage<T> page(long pageSize);

    IPage<T> page(long pageSize, long pageNo);

    /**
     * 数据统计
     * @return 返回表的数据数量
     */
//    Integer count();
}
