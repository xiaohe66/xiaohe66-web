package com.xiaohe66.web.common.util;

import com.xiaohe66.web.common.base.BaseDto;
import com.xiaohe66.web.common.base.BasePo;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xh
 * @date 2018-1-19
 */
public class ClassUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Class.class);

    /**
     * 不同类的同名同类型属性复制
     *
     * @param targetCls 目标类的Class对象<br>
     *                  该类必须要有public的无参构造方法，否则无法通过反射创建实例
     * @param sourceObj 源类的实例
     * @param <T> 目标类
     * @return 目标类的实例
     */
    public static <T extends BaseDto> T convert(Class<T> targetCls, BasePo sourceObj){

        //源为null，则返回null
        if (sourceObj == null) {
            return null;
        }

        /*
        * 判断参数是否为null
        * */
        if(targetCls == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"targetCls is null");
        }

        /*
        * 创建目标类的实例
        * */
        T targetObj;
        try {
            targetObj =  targetCls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("instance err",e);
            return null;
        }

        convert(targetObj,sourceObj);

        return targetObj;
    }

    /**
     * 不同类的同名同类型属性复制
     *
     * @param targetCls 目标类的Class对象<br>
     *                  该类必须要有public的无参构造方法，否则无法通过反射创建实例
     * @param sourceObjList 源类的实例集合
     * @param <T> 目标类
     * @return 目标类的实例集合
     */
    public static <T extends BaseDto> List<T> convertList(Class<T> targetCls, List<? extends BasePo> sourceObjList){
        if(sourceObjList == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"list is null");
        }
        List<T> targetObjList  = new ArrayList<T>();
        for (BasePo basePo : sourceObjList) {
            T targetObj = convert(targetCls,basePo);
            targetObjList.add(targetObj);
        }
        return targetObjList;
    }

    /**
     * 不同类的同名同类型属性复制
     * 把sourceObj实例中的属性值复制到targetObj中，复制的属性必须是同名同类型的
     * 继承的类也可以复制
     *
     * @param targetObj 目标类的实例
     * @param sourceObj 源类的实例
     */
    public static void convert(BaseDto targetObj,BasePo sourceObj){
        /*
        * 判断参数是否为null
        * */
        if(targetObj == null || sourceObj == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"targetObj or sourceObj is null");
        }

        /*
        * 获取源类的Class
        * */
        Class sourceClass =  sourceObj.getClass();

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
        while (sourceOperandCls != null){

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
                while(targetOperandField == null && targetOperandCls != null){
                    try {
                        targetOperandField = targetOperandCls.getDeclaredField(fieldName);
                    } catch (NoSuchFieldException e) {
                        //当前类未获取到，获取父类的
                        targetOperandCls = targetOperandCls.getSuperclass();
                    }
                }

                //未找到相关字段，跳出
                if(targetOperandField == null){
                    continue;
                }

                Class<?> targetOperandFieldType = targetOperandField.getType();
                Class<?> sourceOperandFieldType = sourceOperandField.getType();
                //字段类型相同
                if(targetOperandFieldType.equals(sourceOperandFieldType)){
                    try {
                        //将accessible的值设置为true后，会取消java的访问检查，即可访问私有(private)属性
                        sourceOperandField.setAccessible(true);
                        val = sourceOperandField.get(sourceObj);

                        //设置该属性的值，包括私有属性
                        targetOperandField.setAccessible(true);
                        targetOperandField.set(targetObj,val);

                    } catch (IllegalAccessException e) {
                        LOGGER.error("set value err",e);
                    }
                }
                //字段类型不同，且是Date和String类型
                else if(Date.class.equals(sourceOperandFieldType)&&String.class.equals(targetOperandFieldType)){

                    try {
                        //将accessible的值设置为true后，会取消java的访问检查，即可访问私有(private)属性
                        sourceOperandField.setAccessible(true);
                        val = sourceOperandField.get(sourceObj);

                        //设置该属性的值，包括私有属性
                        if(val != null){
                            targetOperandField.setAccessible(true);
                            targetOperandField.set(targetObj,DateUtils.formatDateTime((Date) val));
                        }

                    } catch (IllegalAccessException e) {
                        LOGGER.error("set value err",e);
                    }
                }

            }
            sourceOperandCls = sourceOperandCls.getSuperclass();
        }
    }

}
