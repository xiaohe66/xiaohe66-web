package com.xiaohe66.web;

import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.spring.XhControllerBeanNameGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xiaohe
 * @time 2019.12.26 15:46
 */
@EnableTransactionManagement
@SpringBootApplication
@MapperScan(basePackages = "com.xiaohe66.web.code", markerInterface = IBaseMapper.class)
@ComponentScan(nameGenerator = XhControllerBeanNameGenerator.class,
        basePackages = {"com.xiaohe66.web.code", "com.xiaohe66.web.config"})
public class Application extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
