package com.xiaohe66.web.base.base.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaohe66.web.base.base.BaseParam;
import com.xiaohe66.web.base.base.BasePo;
import com.xiaohe66.web.base.base.BasePoDetailed;
import com.xiaohe66.web.base.base.BaseService;
import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.base.base.XhPage;
import com.xiaohe66.web.base.util.Check;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
public abstract class AbstractService<M extends IBaseMapper<T>, T extends BasePo>
        extends ServiceImpl<M, T>
        implements BaseService<T> {

    @Override
    public boolean save(T po) {
        Check.notNullCheck(po);
        if (po instanceof BasePoDetailed) {
            BasePoDetailed poDetailed = ((BasePoDetailed) po);
            Integer createId = poDetailed.getCreateId();
            Check.notNullCheck(createId);
            poDetailed.setUpdateId(createId);
        }
        return super.save(po);
    }

    @Override
    public boolean removeById(Serializable id) {
        // todo : updateId没有写入
        return super.removeById(id);
    }

    /**
     * 通用根据参数删除方法(硬删除，数据库不留数据)
     *
     * @param param 传入mybatis的参数
     * @return Integer 删除的数量
     */
    protected Integer removeByParamPhysics(BaseParam param) {
        Check.notNullCheck(param);
        return baseMapper.deleteByParamPhysics(param);
    }

    /**
     * 通用根据参数删除方法(软删除，数据库保留数据)
     *
     * @param param 传入mybatis的参数
     * @return Integer 删除的数量
     */
//    @Override
    protected Integer removeByParam(BaseParam param, Integer currentUsrId) {
        Check.notNullCheck(param);
        // todo : updateId没有写入
        return baseMapper.deleteByParam(param, currentUsrId, new Date());
    }

    /**
     * 通用根据参数更新方法
     *
     * @param po    更新后的实体
     * @param param 传入mybatis的参数
     */
    protected void updateByParam(T po, BaseParam param) {
        Check.notNullCheck(po, param);
        if (po instanceof BasePoDetailed) {
            BasePoDetailed poDetailed = (BasePoDetailed) po;
            Check.notNullCheck(poDetailed.getUpdateId());
        }
        baseMapper.updateByParam(po, param);
    }

    public IPage<T> page(long pageSize) {
        return super.page(new XhPage<>(pageSize));
    }

    public IPage<T> page(long pageSize, Wrapper<T> queryWrapper) {
        return super.page(new XhPage<>(pageSize), queryWrapper);
    }

    public IPage<T> page(long pageSize, long pageNo) {
        return super.page(new XhPage<>(pageSize, pageNo));
    }

    /**
     * 通用根据参数查询方法
     *
     * @param param 传入mybatis的参数
     */
    protected List<T> listByParam(BaseParam param) {
        Check.notNullCheck(param);
        return baseMapper.selectByParam(param);
    }

}
