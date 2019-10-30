package com.xiaohe66.web.base.base.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaohe66.web.base.base.BaseParam;
import com.xiaohe66.web.base.base.BasePo;
import com.xiaohe66.web.base.base.BasePoDetailed;
import com.xiaohe66.web.base.base.BaseService;
import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.base.base.XhPage;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@Slf4j
public abstract class AbstractService<M extends IBaseMapper<P>, P extends BasePo>
        extends ServiceImpl<M, P>
        implements BaseService<P> {

    @Override
    public boolean save(P po) {
        Objects.requireNonNull(po);
        if (po instanceof BasePoDetailed) {
            BasePoDetailed poDetailed = ((BasePoDetailed) po);
            Integer createId = poDetailed.getCreateId();
            Objects.requireNonNull(createId);
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
    /*protected Integer removeByParamPhysics(BaseParam param) {
        Objects.requireNonNull(param);
        return baseMapper.deleteByParamPhysics(param);
    }*/

    /**
     * 通用根据参数删除方法(软删除，数据库保留数据)
     *
     * @param param 传入mybatis的参数
     * @return Integer 删除的数量
     */
//    @Override
    /*protected Integer removeByParam(BaseParam param, Integer currentUsrId) {
        Objects.requireNonNull(param);
        // todo : updateId没有写入
        return baseMapper.deleteByParam(param, currentUsrId, new Date());
    }*/

    /**
     * 通用根据参数更新方法
     *
     * @param po    更新后的实体
     * @param param 传入mybatis的参数
     */
    /*protected void updateByParam(P po, BaseParam param) {
        Objects.requireNonNull(po);
        Objects.requireNonNull(param);
        if (po instanceof BasePoDetailed) {
            BasePoDetailed poDetailed = (BasePoDetailed) po;
            Objects.requireNonNull(poDetailed.getUpdateId());
        }
        baseMapper.updateByParam(po, param);
    }*/

    public IPage<P> page(long pageSize) {
        return super.page(new XhPage<>(pageSize));
    }

    public IPage<P> page(long pageSize, long pageNo) {
        return super.page(new XhPage<>(pageSize, pageNo));
    }

    public IPage<P> page(long pageSize, P po) {
        QueryWrapper<P> queryWrapper = new QueryWrapper<>(po);
        return page(pageSize, queryWrapper);
    }

    public IPage<P> page(long pageSize, Wrapper<P> queryWrapper) {
        return super.page(new XhPage<>(pageSize), queryWrapper);
    }

    @Override
    public final IPage<P> pageDefault(Long pageSize, Long pageNo) {
        XhPage<P> xhPage = new XhPage<>();
        if (pageSize != null) {
            xhPage.setSize(pageSize);
        }
        if (pageNo != null) {
            xhPage.setCurrent(pageNo);
        }
        QueryWrapper<P> queryWrapper = createDefaultQueryWrapper();
        return queryWrapper == null ?
                super.page(xhPage) :
                super.page(xhPage, queryWrapper);
    }

    /**
     * 通用根据参数查询方法
     *
     * @param param 传入mybatis的参数
     */
    /*protected List<P> listByParam(BaseParam param) {
        Objects.requireNonNull(param);
        return baseMapper.selectByParam(param);
    }*/

    @Override
    public QueryWrapper<P> createDefaultQueryWrapper() {
        return null;
    }
}
