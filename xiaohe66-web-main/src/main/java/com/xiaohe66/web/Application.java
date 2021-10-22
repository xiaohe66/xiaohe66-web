package com.xiaohe66.web;

import com.xiaohe66.web.integration.IBaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiaohe
 * @since 2021.10.19 17:23
 */
@MapperScan(basePackageClasses = Application.class, markerInterface = IBaseMapper.class)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}
