package com.xiaohe66.web.base.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaohe66.web.base.base.BaseDto;
import com.xiaohe66.web.base.base.BasePo;
import com.xiaohe66.web.base.base.XhPageDto;
import com.xiaohe66.web.base.exception.param.MissingParamException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * @author xh
 * @time 2018-1-19
 */
@Slf4j
public class ClassUtils {

    private ClassUtils() {
    }

    /**
     * 不同类的同名同类型属性复制（Date对象会自动转换为 String）
     *
     * @param targetCls 目标类的Class对象<br>
     *                  该类必须要有public的无参构造方法，否则无法通过反射创建实例
     * @param sourceObj 源类的实例
     * @param <D>       目标类
     * @return 目标类的实例
     */
    public static <D extends BaseDto> D convert(Class<D> targetCls, BasePo sourceObj) {

        //源为null，则返回null
        if (sourceObj == null) {
            return null;
        }

        /*
         * 判断参数是否为null
         * */
        if (targetCls == null) {
            throw new MissingParamException("targetCls");
        }

        /*
         * 创建目标类的实例
         * */
        D targetObj;
        try {
            targetObj = targetCls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("instance err", e);
            return null;
        }

        convert(targetObj, sourceObj);

        return targetObj;
    }

    /**
     * 不同类的同名同类型属性复制
     * 把sourceObj实例中的属性值复制到targetObj中，（Date对象会自动转换为 String）
     * 继承的类也可以复制，但没有get方法的属性无法复制
     *
     * @param targetObj 目标类的实例
     * @param sourceObj 源类的实例
     */
    public static void convert(BaseDto targetObj, BasePo sourceObj) {
        /*
         * 判断参数是否为null
         * */
        if (targetObj == null || sourceObj == null) {
            throw new MissingParamException("targetObj or sourceObj");
        }


        /*
        *   1.当源类和目标类的属性名称、类型都相同，拷贝结果棒棒哒。
            2.当源对象和目标对象的属性名称相同、类型不同,那么名称相同而类型不同的属性不会被拷贝。
            另外注意，原始类型（int，short，char）和 他们的包装类型，在这里都被当成了不同类型。因此不会被拷贝。

            3.源类或目标类的setter比getter少，拷贝没问题，此时setter多余，但是不会报错。
            4.源类和目标类有相同的属性（两者的getter都存在），但是目标类的setter不存在，
            此时会抛出NullPointerException(这个在高版本bug已经修改测试通过,我使用的49.0)

            链接：https://juejin.im/post/5d37cf95f265da1b7004eb45
        * */
        BeanCopier beanCopier = BeanCopier.create(sourceObj.getClass(), targetObj.getClass(), true);
        beanCopier.copy(sourceObj, targetObj, (sourceValue, targetType, targetMethodName) -> {
            if (sourceValue instanceof Date && String.class.equals(targetType)) {
                return XhDateFormatUtils.yyyyMMddHHmmss.format((Date) sourceValue);
            }

            // todo : 原始类型（int，short，char）和他们的包装类型，是属于不同类型，属性不会被复制

            return sourceValue;
        });
    }

    /**
     * 原自己手写的属性复制方法，现已被 cglib 包的属性复制方法代替
     *
     * @deprecated 原自己手写的属性复制方法，现已被 cglib 包的属性复制方法代替
     */
    @Deprecated
    public static void convertBak(BaseDto targetObj, BasePo sourceObj) {

        if (true) {
            throw new UnsupportedOperationException("请不要调用本方法，本方法弃用");
        }

        /*
         * 判断参数是否为null
         * */
        if (targetObj == null || sourceObj == null) {
            throw new MissingParamException("targetObj or sourceObj");
        }

        /*
         * 获取源类的Class
         * */
        Class sourceClass = sourceObj.getClass();

        /*
         * 源类的字段，一开始保存的是当前类的字段，在循环时，保存的是超类的字段
         * */
        Field[] sourceOperandFields;

        /*
         * 当前操作的源Class。一开始保存的是源类Class，在循环时，保存的是源类的超类Class
         * */
        Class sourceOperandCls = sourceClass;

        /*
         * 目标类，当前操作中的Class
         * */
        Class targetOperandCls;

        /*
         * 当前操作的目标类字段
         * */
        Field targetOperandField;

        /*
         * 字段名
         * */
        String fieldName;
        /*
         * 字段的值
         * */
        Object val;

        //取当前源类，和源的超类
        while (sourceOperandCls != null) {

            sourceOperandFields = sourceOperandCls.getDeclaredFields();

            for (Field sourceOperandField : sourceOperandFields) {
                //初始化目标class
                targetOperandCls = targetObj.getClass();

                //获取源成员名称
                fieldName = sourceOperandField.getName();

                targetOperandField = null;
                /*
                 * 遍历每一个目标类的超类
                 * 获取目标的Field对象，当前类找不到时，就去超类找
                 * */
                while (targetOperandField == null && targetOperandCls != null) {
                    try {
                        targetOperandField = targetOperandCls.getDeclaredField(fieldName);
                    } catch (NoSuchFieldException e) {
                        //当前类未获取到，获取父类的
                        targetOperandCls = targetOperandCls.getSuperclass();
                    }
                }

                //未找到相关字段，跳出
                if (targetOperandField == null) {
                    continue;
                }

                Class<?> targetOperandFieldType = targetOperandField.getType();
                Class<?> sourceOperandFieldType = sourceOperandField.getType();
                //字段类型相同
                if (targetOperandFieldType.equals(sourceOperandFieldType)) {
                    try {
                        //将accessible的值设置为true后，会取消java的访问检查，即可访问私有(private)属性
                        sourceOperandField.setAccessible(true);
                        val = sourceOperandField.get(sourceObj);

                        //设置该属性的值，包括私有属性
                        targetOperandField.setAccessible(true);
                        targetOperandField.set(targetObj, val);

                    } catch (IllegalAccessException e) {
                        log.error("set value err", e);
                    }
                }
                //字段类型不同，且是Date和String类型
                else if (Date.class.equals(sourceOperandFieldType) && String.class.equals(targetOperandFieldType)) {

                    try {
                        //将accessible的值设置为true后，会取消java的访问检查，即可访问私有(private)属性
                        sourceOperandField.setAccessible(true);
                        val = sourceOperandField.get(sourceObj);

                        //设置该属性的值，包括私有属性
                        if (val != null) {
                            targetOperandField.setAccessible(true);
                            targetOperandField.set(targetObj, XhDateFormatUtils.yyyyMMddHHmmss.format((Date) val));
                        }

                    } catch (IllegalAccessException e) {
                        log.error("set value err", e);
                    }
                }

            }
            sourceOperandCls = sourceOperandCls.getSuperclass();
        }
    }

    /**
     * 不同类的同名同类型属性复制
     *
     * @param targetCls     目标类的Class对象<br>
     *                      该类必须要有public的无参构造方法，否则无法通过反射创建实例
     * @param sourceObjList 源类的实例集合
     * @param <D>           目标类
     * @return 目标类的实例集合
     */
    public static <D extends BaseDto, P extends BasePo> List<D> convert(Class<D> targetCls, List<P> sourceObjList) {
        return convert(targetCls, sourceObjList, null);
    }

    /**
     * 不同类的同名同类型属性复制，本方法支持转换中调用自定义方法
     *
     * @param targetCls     目标类的Class对象<br>
     *                      该类必须要有public的无参构造方法，否则无法通过反射创建实例
     * @param sourceObjList 源类的实例集合
     * @param <D>           目标类
     * @param task          转换中自定义任务
     * @return 目标类的实例集合
     */
    public static <D extends BaseDto, P extends BasePo> List<D> convert(Class<D> targetCls, List<P> sourceObjList, BiConsumer<D, P> task) {
        if (sourceObjList == null) {
            throw new MissingParamException("list");
        }
        List<D> targetObjList = new ArrayList<>(sourceObjList.size());

        for (P basePo : sourceObjList) {
            D targetObj = convert(targetCls, basePo);
            targetObjList.add(targetObj);
            if (task != null) {
                task.accept(targetObj, basePo);
            }
        }
        return targetObjList;
    }

    public static <D extends BaseDto, P extends BasePo> IPage<D> convert(Class<D> targetCls, IPage<P> sourcePage) {
        return convert(targetCls, sourcePage, null);
    }

    public static <D extends BaseDto, P extends BasePo> IPage<D> convert(Class<D> targetCls, IPage<P> sourcePage, BiConsumer<D, P> task) {
        XhPageDto<D> targetPage = new XhPageDto<>();

        targetPage.setPages(sourcePage.getPages());
        targetPage.setTotal(sourcePage.getTotal());
        targetPage.setCurrent(sourcePage.getCurrent());
        targetPage.setSize(sourcePage.getSize());

        List<P> sourceList = sourcePage.getRecords();
        List<D> targetList = new ArrayList<>(sourceList.size());
        for (P basePo : sourcePage.getRecords()) {
            D targetObj = convert(targetCls, basePo);
            targetList.add(targetObj);
            if (task != null) {
                task.accept(targetObj, basePo);
            }
        }
        targetPage.setRecords(targetList);
        targetPage.getPages();
        return targetPage;
    }

}
