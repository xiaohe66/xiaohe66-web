package com.xiaohe66.web.base.base;

import java.util.List;

/**
 * 基础service，包含基础增删改查方法
 *
 * @author xiaohe
 * @time 17-10-28 028
 */
public interface BaseService<T extends BasePo> {
    /**
     * 通用单个插入方法
     * @param po 插入的实体
     * @param currentUsrId 当前操作者id
     */
    void add(T po,Long currentUsrId);

    /**
     * 通用批量插入方法
     * @param list 批量插入的实体list
     * @param currentUsrId 当前操作者id
     */
    void addAll(List<T> list,Long currentUsrId);

    /**
     * 通用根据id删除方法(硬删除，数据库不留数据)
     * @param id 待删除的数据库id
     */
    void delByIdOfHard(Long id);

    /**
     * 通用根据id删除方法(软删除，数据库保留数据)
     * @param id 待删除的数据库id
     * @param currentUsrId 当前登录用户id
     */
    void delById(Long id,Long currentUsrId);

    /*
     * 通用根据参数删除方法(硬删除，数据库不留数据)
     * @param param 传入mybatis的参数
     * @return long 删除的数量
     */
//    long delByParamOfHard(BaseParam param);

    /*
     * 通用根据参数删除方法(软删除，数据库保留数据)
     * @param param 传入mybatis的参数
     * @param currentUsrId 当前登录用户id
     * @return long 删除的数量
     */
//    long delByParam(BaseParam param,Long currentUsrId);

    /**
     * 通用更新方法
     * @param po 更新后的实体
     * @param currentUsrId 当前操作者id
     */
    void updateById(T po, Long currentUsrId);

    /*
     * 通用更新方法，根据参数更新
     * @param po 更新后的实体
     * @param param 更新的参数
     * @param currentUsrId 当前操作者id
     */
//    void updateByParam(T po, BaseParam param, Long currentUsrId);

    /**
     * 通用根据id查询方法
     * @param id 查询id
     * @return 对应实体
     */
    T findById(Long id);

    /*
     * 通用根据参数查询方法
     * @param param 查询参数
     * @return 对应实体list
     */
//    List<T> findByParam(BaseParam param);

    /**
     * 数据统计
     * @return  返回表的数据数量
     */
    Long count();
}
