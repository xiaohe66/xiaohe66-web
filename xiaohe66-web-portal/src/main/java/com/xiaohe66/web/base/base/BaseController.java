package com.xiaohe66.web.base.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaohe66.web.base.annotation.Del;
import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.exception.sec.MissingFunctionException;
import com.xiaohe66.web.base.exception.sec.MissingRoleException;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.org.helper.UserHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author xiaohe
 * @time 2019.10.12 11:34
 */
@Slf4j
public abstract class BaseController<S extends AbstractService<? extends IBaseMapper, P>, P
        extends BasePo, D extends BaseDto> {

    @Autowired
    protected S baseService;

    protected final String moduleName;

    protected final Class<D> dtoClass;

    @SuppressWarnings("unchecked")
    public BaseController() {
        Type[] type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();

        Class<P> poClass = ((Class<P>) type[1]);
        String poClassName = poClass.getSimpleName();
        char[] chars = poClassName.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        moduleName = new String(chars);

        dtoClass = ((Class<D>) type[2]);
        log.info("moduleName : {}, dtoClass : {}", moduleName, dtoClass.getName());
    }

    @Post
    public Result post(P po) {
        checkSave(po);
        if (po instanceof BasePoDetailed) {
            Integer currentUsrId = UserHelper.getCurrentUsrIdNotEx();
            ((BasePoDetailed) po).setCreateId(currentUsrId);
        }
        baseService.save(po);
        return Result.ok(po.getId());
    }

    @Del("/{id}")
    public Result del(@PathVariable("id") Integer id) {
        checkDelete(id);
        return Result.ok(baseService.removeById(id));
    }

    @Put
    public Result put(P po) {
        checkUpdate(po);
        return Result.ok(baseService.updateById(po));
    }

    @SuppressWarnings("unchecked")
    @Get("/{id}")
    public Result get(@PathVariable("id") Integer id) {
        checkSelect(null);
        P po = baseService.getById(id);
        D dto = ClassUtils.convert(dtoClass, po);

        if (baseService instanceof DtoConverter) {
            ((DtoConverter) baseService).convertDto(dto, po);
        }
        return Result.ok(dto);
    }

    @SuppressWarnings("unchecked")
    @Get
    public Result list(@RequestHeader(value = Final.HeaderKey.PAGE_SIZE, required = false) Long pageSize,
                       @RequestHeader(value = Final.HeaderKey.PAGE_NO, required = false) Long pageNo,
                       P po) {
        checkSelect(po);

        IPage<P> poPage = baseService.pageDefault(pageSize, pageNo, po);

        IPage<D> dtoPage;
        if (baseService instanceof DtoConverter) {
            DtoConverter<P, D> dtoConverter = (DtoConverter) this.baseService;
            dtoPage = ClassUtils.convert(dtoClass, poPage, dtoConverter::convertDto);
        } else {
            dtoPage = ClassUtils.convert(dtoClass, poPage);
        }
        return Result.ok(dtoPage);
    }

    protected void checkSave(P po) {
        checkSavePermitted();
    }

    protected void checkDelete(Integer id) {
        checkDeletePermitted();
    }

    protected void checkUpdate(P po) {
        checkUpdatePermitted();
    }

    protected void checkSelect(P po) {
        checkSelectPermitted();
    }

    protected void checkSavePermitted() {
        checkPermitted(moduleName + ":insert");
    }

    protected void checkDeletePermitted() {
        checkPermitted(moduleName + ":delete");
    }

    protected void checkUpdatePermitted() {
        checkPermitted(moduleName + ":update");
    }

    protected void checkSelectPermitted() {
        checkPermitted(moduleName + ":select");
    }

    protected final void checkPermitted(String permittedName) {
        if (!SecurityUtils.getSubject().isPermittedAll(permittedName)) {
            throw new MissingFunctionException(permittedName);
        }
    }

    protected final void checkRole(String roleName) {
        if (!SecurityUtils.getSubject().hasRole(roleName)) {
            throw new MissingRoleException(roleName);
        }
    }
}
