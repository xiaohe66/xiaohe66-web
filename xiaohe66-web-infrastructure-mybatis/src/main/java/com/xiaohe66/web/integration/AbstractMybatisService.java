package com.xiaohe66.web.integration;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaohe66.web.integration.domain.DataConverter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiaohe
 * @since 2021.08.12 10:03
 */
public abstract class AbstractMybatisService<C extends DataConverter, M extends IBaseMapper<T>, T>
        extends ServiceImpl<M, T>
        implements IBaseService<T> {

    @Autowired
    protected C dataConverter;


}
