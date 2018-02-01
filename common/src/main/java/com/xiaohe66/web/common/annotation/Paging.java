package com.xiaohe66.web.common.annotation;

import java.lang.annotation.*;

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
