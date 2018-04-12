package com.xiaohe66.web.common.base.impl;

import com.xiaohe66.web.common.base.*;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;

import java.util.Date;
import java.util.List;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
public abstract class AbstractService<T extends BasePo> implements BaseService<T> {

    private BaseDao<T> baseDao;

    protected AbstractService(){

    }

    public AbstractService(BaseDao<T> baseDao){
        this.baseDao = baseDao;
    }

    /**
     * 单个插入
     * @param po 插入的实体
     * @param currentUsrId 当前登录用户id
     */
    @Override
    public void add(T po, Long currentUsrId) {
        daoNotNullCheck();
        if(po == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"po is null");
        }
        if(po instanceof BasePoDetailed){
            BasePoDetailed poDetailed = ((BasePoDetailed) po);
            poDetailed.setCreateId(currentUsrId);
            poDetailed.setCreateTime(new Date());
            poDetailed.setUpdateId(currentUsrId);
        }
        baseDao.add(po);
    }

    /**
     * 批量插入
     * @param list 批量插入的实体
     * @param currentUsrId 当前登录用户id
     */
    @Override
    public void addAll(List<T> list,Long currentUsrId) {
        daoNotNullCheck();
        if(list == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"list is null");
        }
        if(list.size() == 0){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"list size is 0");
        }
        if(list.get(0) instanceof BasePoDetailed){
            for (T po : list) {
                BasePoDetailed poDetailed = ((BasePoDetailed) po);
                poDetailed.setCreateId(currentUsrId);
                poDetailed.setUpdateId(currentUsrId);
            }
        }
        baseDao.addAll(list);
    }

    /**
     * 通用根据id删除方法(硬删除，数据库不留数据)
     * @param id 待删除的数据库id
     */
    @Override
    public void delByIdOfHard(Long id){
        daoNotNullCheck();
        if(id == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"id is null");
        }

        baseDao.delByIdOfHard(id);
    }

    /**
     * 通用根据id删除方法(软删除，数据库保留数据)
     * @param id 待删除的数据库id
     */
    @Override
    public void delById(Long id,Long currentUsrId){
        daoNotNullCheck();
        if(id == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"id is null");
        }
        baseDao.delById(id,currentUsrId,new Date(System.currentTimeMillis()));
    }


    /**
     * 通用根据参数删除方法(硬删除，数据库不留数据)
     * @param param 传入mybatis的参数
     * @return long 删除的数量
     */
//    @Override
    protected long delByParamOfHard(BaseParam param){
        if (param == null) {
            throw new XhException(CodeEnum.NULL_EXCEPTION,"param is null");
        }
        return baseDao.delByParamOfHard(param);
    }

    /**
     * 通用根据参数删除方法(软删除，数据库保留数据)
     * @param param 传入mybatis的参数
     * @return long 删除的数量
     */
//    @Override
    protected long delByParam(BaseParam param,Long currentUsrId){
        if (param == null) {
            throw new XhException(CodeEnum.NULL_EXCEPTION,"param is null");
        }
        return baseDao.delByParam(param,currentUsrId,new Date(System.currentTimeMillis()));
    }

    /**
     * 根据id更新
     * @param po 更新后的实体，id保存于po中
     * @param currentUsrId 当前登录用户id
     */
    @Override
    public void updateById(T po, Long currentUsrId) {
        daoNotNullCheck();
        if(po == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"po is null");
        }
        if(po.getId() == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"po of id is null");
        }
        if (po instanceof BasePoDetailed) {
            BasePoDetailed poDetailed = ((BasePoDetailed) po);
            poDetailed.setUpdateId(currentUsrId);
            poDetailed.setUpdateTime(new Date());
        }
        baseDao.updateById(po);
    }

    /**
     * 根据id查询
     * @param id 查询的id
     * @return 对应id实体
     */
    @Override
    public T findById(Long id) {
        daoNotNullCheck();
        if(id == null){
            throw new NullPointerException("id is null");
        }
        return baseDao.findById(id);
    }

    /**
     * 通用根据参数更新方法
     * @param po 更新后的实体
     * @param param 传入mybatis的参数
     * @param currentUsrId 当前登录用户id
     */
    protected void updateByParam(T po, BaseParam param, Long currentUsrId) {
        if (po == null) {
            throw new XhException(CodeEnum.NULL_EXCEPTION,"po is null");
        }
        if (param == null) {
            throw new XhException(CodeEnum.NULL_EXCEPTION,"param is null");
        }
        if(po instanceof BasePoDetailed){
            if (currentUsrId == null) {
                throw new XhException(CodeEnum.NULL_EXCEPTION,"currentUsrId is null");
            }
            BasePoDetailed basePoDetailed = ((BasePoDetailed) po);
            basePoDetailed.setUpdateId(currentUsrId);
            basePoDetailed.setUpdateTime(new Date());
        }
        baseDao.updateByParam(po,param);
    }
    /**
     * 通用根据参数查询方法
     * @param param 传入mybatis的参数
     */
    protected List<T> findByParam(BaseParam param) {
        daoNotNullCheck();
        return baseDao.findByParam(param);
    }

    private void daoNotNullCheck(){
        if(baseDao == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"baseDao not init");
        }
    }

    /**
     * 数量统计
     * @return  返回表的数据数量
     */
    @Override
    public Long count() {
        daoNotNullCheck();
        return baseDao.count();
    }
}
