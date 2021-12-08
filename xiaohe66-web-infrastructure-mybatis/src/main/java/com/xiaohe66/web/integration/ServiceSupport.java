package com.xiaohe66.web.integration;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.08 14:42
 */
public class ServiceSupport<M extends IBaseMapper<T>, T extends IDo> extends ServiceImpl<M, T> {

    public T getByCreateId(Serializable id) {

        QueryWrapper<T> queryWrapper = new QueryWrapper<T>()
                .eq("create_id", id)
                .last("limit 1");

        return getOne(queryWrapper);
    }

    public List<T> listByCreateId(Serializable id) {

        QueryWrapper<T> queryWrapper = new QueryWrapper<T>()
                .eq("create_id", id);

        return list(queryWrapper);
    }

    public boolean removeByCreateId(Serializable id) {

        QueryWrapper<T> queryWrapper = new QueryWrapper<T>()
                .eq("create_id", id);

        return remove(queryWrapper);
    }

}
