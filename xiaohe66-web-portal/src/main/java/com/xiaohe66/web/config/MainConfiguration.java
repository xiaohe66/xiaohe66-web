package com.xiaohe66.web.config;

import com.xiaohe66.web.base.holder.ApplicationContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaohe
 * @time 2019.10.12 17:05
 */
@Configuration
public class MainConfiguration {

    @Bean
    public ApplicationContextHolder applicationContextHolder(){
        return new ApplicationContextHolder();
    }

}
