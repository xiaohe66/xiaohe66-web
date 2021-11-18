package com.xiaohe66.web.integration.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.18 17:52
 */
public class MyLogicSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> list = super.getMethodList(mapperClass);

        list.add(new IsExistId());

        return list;
    }
}
