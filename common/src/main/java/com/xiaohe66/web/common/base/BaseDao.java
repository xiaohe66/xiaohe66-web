package com.xiaohe66.web.common.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 基础dao
 *
 * @author xiaohe
 * @time 17-10-28 028
 */
public interface BaseDao<T extends BasePo>{
    /**
     * 通用单个插入方法
     * @param po 插入的实体
     */
    void add(T po);

    /**
     * 通用批量插入方法
     * @param list 批量插入的实体list
     */
    void addAll(@Param("list") List<T> list);

    /**
     * 通用根据id删除方法(硬删除，数据库不留数据)
     * @param id 待删除的数据库id
     */
    void delOfHard(long id);

    /**
     * 通用根据id删除方法(软删除，数据库保留数据)
     * @param id 待删除的数据库id
     */
    void delOfSoft(long id);

    /**
     * 通用根据参数删除方法(硬删除，数据库不留数据)
     * @param param 传入mybatis的参数
     * @return long 删除的数量
     */
    long delOfHard(@Param("param") BaseParam param);

    /**
     * 通用根据参数删除方法(软删除，数据库保留数据)
     * @param param 传入mybatis的参数
     * @return long 删除的数量
     */
    long delOfSoft(@Param("param") BaseParam param);

    /**
     * 通用更新方法
     * @param po 更新后的实体
     */
    void update(T po);

    /**
     * 通用更新方法，根据参数更新
     * @param po 更新后的实体
     * @param param 更新的参数
     */
    void update(T po,@Param("param")  BaseParam param);

    /**
     * 通用根据id查询方法
     * @param id 查询id
     * @return 对应实体
     */
    T searchById(Long id);

    /**
     * 通用根据参数查询方法
     * @param param 查询参数
     * @return 对应实体list
     */
    List<T> searchByParam(@Param("param") BaseParam param);
}
