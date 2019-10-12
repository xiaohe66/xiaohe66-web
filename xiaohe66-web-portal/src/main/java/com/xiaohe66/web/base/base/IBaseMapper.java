package com.xiaohe66.web.base.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 基础dao
 *
 * @author xiaohe
 * @time 17-10-28 028
 */
public interface IBaseMapper<T extends BasePo> extends BaseMapper<T> {
    /**
     * 通用单个插入方法
     * @param po 插入的实体
     */
//    void add(T po);

    /**
     * 通用批量插入方法
     * @param list 批量插入的实体list
     */
//    void addAll(@Param("list") List<T> list);

    /**
     * 通用根据id删除方法(硬删除，数据库不留数据)
     * @param id 待删除的数据库id
     */
//    void delByIdOfHard(Integer id);

    /**
     * 通用根据id删除方法(软删除，数据库保留数据)
     * @param id 待删除的数据库id
     * @param currentUsrId 当前登录用户id
     * @param updateTime 删除时间
     */
//    void delById(@Param("id") Integer id, @Param("currentUsrId") Integer currentUsrId, @Param("updateTime") Date updateTime);

    /**
     * 通用根据参数删除方法(硬删除，数据库不留数据)
     * @param param 传入mybatis的参数
     * @return Integer 删除的数量
     */
    Integer delByParamPhysics(@Param("param") BaseParam param);

    /**
     * 通用根据参数删除方法(软删除，数据库保留数据)
     * @param param 传入mybatis的参数
     * @param currentUsrId 当前登录用户id
     * @param updateTime 删除时间
     *
     * @return Integer 删除的数量
     */
    Integer delByParam(@Param("param") BaseParam param, @Param("currentUsrId") Integer currentUsrId, @Param("updateTime") Date updateTime);

    /**
     * 通用更新方法，根据参数更新
     * @param po 更新后的实体
     * @param param 更新的参数
     */
    void updateByParam(T po, @Param("param") BaseParam param);

    /**
     * 通用根据id查询方法
     * @param id 查询id
     * @return 对应实体
     */
//    T findById(Integer id);

    /**
     * 通用根据参数查询方法
     * @param param 查询参数
     * @return 对应实体list
     */
    List<T> listByParam(@Param("param") BaseParam param);

    /**
     * 数据统计
     * @return  返回表的数据数量
     */
//    Integer count();
}
