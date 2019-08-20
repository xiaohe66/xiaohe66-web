package com.xiaohe66.web.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用该注解的Controller方法会自动调用mybatis分页方法
 *
 * @author xiaohe
 * @time 17-11-11 011
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Paging {
    String value() default "";
}
