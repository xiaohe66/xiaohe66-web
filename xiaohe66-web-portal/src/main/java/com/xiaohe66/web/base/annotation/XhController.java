package com.xiaohe66.web.base.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Restful风格
 * 在类上使用该注解的可以使该类成为一个controller
 * todo:使用该注解的bean名称变成了value所指定的值，需要修改
 *
 * @author xh
 * @date 2017/10/30
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping
@Controller
public @interface XhController {

    @AliasFor(annotation = Controller.class,attribute = "value")
    String name() default "";

    @AliasFor(annotation = RequestMapping.class)
    String value() default "";
}