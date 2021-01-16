package com.xiaohe66.web;

import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.sys.spring.XhControllerBeanNameGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xiaohe
 * @time 2019.12.26 15:46
 */
@EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true)
@EnableTransactionManagement
@SpringBootApplication
@MapperScan(basePackages = "com.xiaohe66.web.code", markerInterface = IBaseMapper.class)
@ComponentScan(nameGenerator = XhControllerBeanNameGenerator.class,
        basePackages = {"com.xiaohe66.web.code", "com.xiaohe66.web.config", "com.xiaohe66.web.sys.aop"})
public class Application implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 增加这个资源映射，是为了前端页面在引用时，可以有代码提示
        // 前端在引用时，若不加 static,则不会出现url提示
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
