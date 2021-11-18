package com.xiaohe66.web.integration;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

/**
 * @author xiaohe
 * @since 2021.08.11 18:34
 */
public interface IBaseMapper<T> extends BaseMapper<T> {

    boolean isExistId(Serializable id);

}
