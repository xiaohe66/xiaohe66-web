package com.xiaohe66.web.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

/**
 * Restful风格
 * 在类上使用该注解的可以使该类成为一个controller
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

//    @AliasFor(annotation = RequestMapping.class,attribute = "value")
//    String url() default "";

    @AliasFor(annotation = RequestMapping.class)
    String value() default "";
}