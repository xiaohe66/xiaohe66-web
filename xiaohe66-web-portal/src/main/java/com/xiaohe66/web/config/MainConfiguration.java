package com.xiaohe66.web.config;

import com.xiaohe66.web.base.holder.ApplicationContextHolder;
import com.xiaohe66.web.sys.filter.LoginFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaohe
 * @time 2019.10.12 17:05
 */
@Configuration
public class MainConfiguration {

    @Bean
    public ApplicationContextHolder applicationContextHolder() {
        return new ApplicationContextHolder();
    }

    @Bean
    @ConditionalOnProperty(value = "debug", havingValue = "true")
    public LoginFilter loginFilter() {
        return new LoginFilter();
    }

}
