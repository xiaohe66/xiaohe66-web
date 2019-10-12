package com.xiaohe66.web.base.base.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaohe66.web.base.base.BaseDao;
import com.xiaohe66.web.base.base.BaseParam;
import com.xiaohe66.web.base.base.BasePo;
import com.xiaohe66.web.base.base.BasePoDetailed;
import com.xiaohe66.web.base.base.BaseService;
import com.xiaohe66.web.base.util.Check;

import java.util.Date;
import java.util.List;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
public abstract class AbstractService<M extends BaseDao<T>, T extends BasePo>
        extends ServiceImpl<M, T>
        implements BaseService<T> {

    /**
     * 单个插入
     *
     * @param po           插入的实体
     * @param currentUsrId 当前登录用户id
     */
    @Override
    public void add(T po, Integer currentUsrId) {
        Check.notNullCheck(po);
        if (po instanceof BasePoDetailed) {
            BasePoDetailed poDetailed = ((BasePoDetailed) po);
            poDetailed.setCreateId(currentUsrId);
            poDetailed.setUpdateId(currentUsrId);
        }
        save(po);
    }

    /**
     * 批量插入
     *
     * @param list         批量插入的实体
     * @param currentUsrId 当前登录用户id
     */
    @Override
    public void addAll(List<T> list, Integer currentUsrId) {
        Check.notEmptyCheck(list);
        if (list.get(0) instanceof BasePoDetailed) {
            for (T po : list) {
                BasePoDetailed poDetailed = ((BasePoDetailed) po);
                poDetailed.setCreateId(currentUsrId);
                poDetailed.setUpdateId(currentUsrId);
            }
        }
        saveBatch(list);
    }

    /**
     * 通用根据id删除方法(硬删除，数据库不留数据)
     *
     * @param id 待删除的数据库id
     */
    @Override
    public void delByIdOfHard(Integer id) {
        Check.notNullCheck(id);
        removeById(id);
    }

    /**
     * 通用根据id删除方法(软删除，数据库保留数据)
     *
     * @param id 待删除的数据库id
     */
    @Override
    public void delById(Integer id, Integer currentUsrId) {
        Check.notNullCheck(id);
        // todo : updateId
        removeById(id);
    }


    /**
     * 通用根据参数删除方法(硬删除，数据库不留数据)
     *
     * @param param 传入mybatis的参数
     * @return Integer 删除的数量
     */
//    @Override
    protected Integer delByParamOfHard(BaseParam param) {
        Check.notNullCheck(param);
        return baseMapper.delByParamPhysics(param);
    }

    /**
     * 通用根据参数删除方法(软删除，数据库保留数据)
     *
     * @param param 传入mybatis的参数
     * @return Integer 删除的数量
     */
//    @Override
    protected Integer delByParam(BaseParam param, Integer currentUsrId) {
        Check.notNullCheck(param);
        // todo : isDelete
        return baseMapper.delByParam(param, currentUsrId, new Date());
    }

    /**
     * 根据id更新
     *
     * @param po           更新后的实体，id保存于po中
     * @param currentUsrId 当前登录用户id
     */
    @Override
    public void updateById(T po, Integer currentUsrId) {
        Check.notNullCheck(po);
        Check.notNullCheck(po.getId());

        if (po instanceof BasePoDetailed) {
            BasePoDetailed poDetailed = ((BasePoDetailed) po);
            poDetailed.setUpdateId(currentUsrId);
        }
        updateById(po);
    }

    /**
     * 根据id查询
     *
     * @param id 查询的id
     * @return 对应id实体
     */
    @Override
    public T findById(Integer id) {
        Check.notNullCheck(id);
        return getById(id);
    }

    /**
     * 通用根据参数更新方法
     *
     * @param po           更新后的实体
     * @param param        传入mybatis的参数
     * @param currentUsrId 当前登录用户id
     */
    protected void updateByParam(T po, BaseParam param, Integer currentUsrId) {
        Check.notNullCheck(po, param);
        if (po instanceof BasePoDetailed) {
            Check.notNullCheck(currentUsrId);
            BasePoDetailed basePoDetailed = ((BasePoDetailed) po);
            basePoDetailed.setUpdateId(currentUsrId);
        }
        baseMapper.updateByParam(po, param);
    }

    /**
     * 通用根据参数查询方法
     *
     * @param param 传入mybatis的参数
     */
    protected List<T> findByParam(BaseParam param) {
        Check.notNullCheck(param);
        return baseMapper.listByParam(param);
    }

}
