package com.xiaohe66.web.base.base;

import com.xiaohe66.web.base.annotation.Del;
import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.org.helper.UsrHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author xiaohe
 * @time 2019.10.12 11:34
 */
public abstract class BaseController<S extends AbstractService<? extends IBaseMapper, T>, T extends BasePo, D extends BaseDto> {

    @Autowired
    protected S baseService;

    @Post
    public Result post(T po) {
        if(po instanceof BasePoDetailed){
            Integer currentUsrId = UsrHelper.getCurrentUsrId();
            ((BasePoDetailed) po).setCreateId(currentUsrId);
        }
        return Result.ok(baseService.save(po));
    }

    @Del("/{id}")
    public Result del(@PathVariable("id") Integer id) {
        return Result.ok(baseService.removeById(id));
    }

    @Put
    public Result put(T po) {
        if(po instanceof BasePoDetailed){
            Integer currentUsrId = UsrHelper.getCurrentUsrId();
            ((BasePoDetailed) po).setUpdateId(currentUsrId);
        }
        return Result.ok(baseService.updateById(po));
    }

    @Get("/{id}")
    public Result get(@PathVariable("id") Integer id) {
        T po = baseService.getById(id);
        D dto = ClassUtils.convert(dtoClass(), po);
        convertTask(dto, po);
        return Result.ok(dto);
    }

    @Get
    public Result list() {
        List<T> poList = baseService.list();
        List<D> dtoList = ClassUtils.convertList(dtoClass(), poList, this::convertTask);
        return Result.ok(dtoList);
    }

    protected void convertTask(D dto, T po) {

    }

    protected abstract Class<D> dtoClass();
}
