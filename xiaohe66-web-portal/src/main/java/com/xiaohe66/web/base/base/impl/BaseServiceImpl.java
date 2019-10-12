package com.xiaohe66.web.base.base.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.base.base.BasePo;

/**
 * @author xiaohe
 * @time 2019.10.11 17:43
 */
public abstract class BaseServiceImpl<M extends IBaseMapper<T>,T extends BasePo> extends ServiceImpl<M,T> {
}
