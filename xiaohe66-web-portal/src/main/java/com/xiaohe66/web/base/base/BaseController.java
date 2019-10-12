package com.xiaohe66.web.base.base;

import com.xiaohe66.web.base.annotation.Del;
import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.base.impl.BaseServiceImpl;
import com.xiaohe66.web.base.data.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xiaohe
 * @time 2019.10.12 11:34
 */
public class BaseController<S extends BaseServiceImpl> {

    @Autowired
    protected S service;

    @Get("/{id}")
    public Result get(@PathVariable("id") Integer id){
        return Result.ok(service.getById(id));
    }

    @Del("/{id}")
    public Result del(@PathVariable("id") Integer id){
        return Result.ok(service.removeById(id));
    }
}
