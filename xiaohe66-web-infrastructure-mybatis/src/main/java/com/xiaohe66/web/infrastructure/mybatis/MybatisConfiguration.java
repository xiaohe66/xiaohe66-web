package com.xiaohe66.web.infrastructure.mybatis;

import com.xiaohe66.web.integration.injector.MyLogicSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaohe
 * @since 2021.11.18 18:14
 */
@Configuration
public class MybatisConfiguration {

    @Bean
    public MyLogicSqlInjector myLogicSqlInjector() {
        return new MyLogicSqlInjector();
    }

}
