package com.xiaohe66.web;

import com.xiaohe66.web.integration.MapperSupport;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xiaohe
 * @since 2021.10.19 17:23
 */
@MapperScan(basePackageClasses = Application.class, markerInterface = MapperSupport.class)
@EnableTransactionManagement
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
