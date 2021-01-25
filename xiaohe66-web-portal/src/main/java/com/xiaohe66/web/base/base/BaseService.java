package com.xiaohe66.web.base.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 基础service，包含基础增删改查方法
 *
 * @author xiaohe
 * @time 17-10-28 028
 */
public interface BaseService<P extends BasePo> extends IService<P> {

    /**
     * 根据传入的ID，取出大于该ID的指定数量的集合
     *
     * @param pageSize 数量
     * @param startId  初始ID
     * @param po       搜索实体
     * @return 结果集
     */
    List<P> listStartId(Long pageSize, Long startId, P po);

    IPage<P> page(long pageSize);

    IPage<P> page(long pageSize, long pageNo);

    IPage<P> page(long pageSize, P po);

    IPage<P> pageDefault(Long pageSize, Long pageNo, P po);

    /**
     * 返回默认的查询器，分页查询、byId查询等查询会自动使用该查询器
     *
     * @param po 参数
     * @return 查询器
     */
    QueryWrapper<P> createDefaultQueryWrapper(P po);
}
