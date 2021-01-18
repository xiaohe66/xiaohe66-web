package com.xiaohe66.web.base.base.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaohe66.web.base.base.BasePo;
import com.xiaohe66.web.base.base.BasePoDetailed;
import com.xiaohe66.web.base.base.BaseService;
import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.base.base.XhPage;
import com.xiaohe66.web.code.org.helper.UserHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *
 * todo : 增加 checkPo 方法
 *
 * @author xiaohe
 * @time 17-10-28 028
 */
@Slf4j
public abstract class AbstractService<M extends IBaseMapper<P>, P extends BasePo>
        extends ServiceImpl<M, P>
        implements BaseService<P> {

    @Override
    public boolean save(P po) {
        if (po instanceof BasePoDetailed) {
            Integer currentUserId = UserHelper.getCurrentUsrIdNotEx();
            if (currentUserId != null) {
                BasePoDetailed detailed = (BasePoDetailed) po;
                detailed.setCreateId(currentUserId);
                detailed.setUpdateId(currentUserId);
            }
        }
        return super.save(po);
    }

    @Override
    public boolean updateById(P po) {
        if (po instanceof BasePoDetailed) {
            Integer currentUserId = UserHelper.getCurrentUsrIdNotEx();
            if (currentUserId != null) {
                ((BasePoDetailed) po).setUpdateId(currentUserId);
            }
        }
        return super.updateById(po);
    }

    @Override
    public IPage<P> page(long pageSize) {
        return super.page(new XhPage<>(pageSize));
    }

    @Override
    public IPage<P> page(long pageSize, long pageNo) {
        return super.page(new XhPage<>(pageSize, pageNo));
    }

    @Override
    public IPage<P> page(long pageSize, P po) {
        QueryWrapper<P> queryWrapper = new QueryWrapper<>(po);
        return page(pageSize, queryWrapper);
    }

    public IPage<P> page(long pageSize, Wrapper<P> queryWrapper) {
        return super.page(new XhPage<>(pageSize), queryWrapper);
    }

    @Override
    public IPage<P> pageDefault(Long pageSize, Long pageNo, P po) {
        XhPage<P> xhPage = new XhPage<>();
        if (pageSize != null) {
            xhPage.setSize(pageSize);
        }
        if (pageNo != null) {
            xhPage.setCurrent(pageNo);
        }

        if(po == null){
            return page(xhPage);

        }else{
            return page(xhPage, createDefaultQueryWrapper(po));
        }
    }

    @Override
    public List<P> listStartId(Long pageSize, Long startId, P po) {

        QueryWrapper<P> queryWrapper = createDefaultQueryWrapper(po);

        queryWrapper.gt("id",startId);
        queryWrapper.last("limit "+pageSize);

        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public QueryWrapper<P> createDefaultQueryWrapper(P po) {
        return new QueryWrapper<>(po);
    }
}
