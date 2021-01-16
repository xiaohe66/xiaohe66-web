package com.xiaohe66.web.config;

import com.xiaohe66.web.base.holder.ApplicationContextHolder;
import com.xiaohe66.web.code.security.service.LoginService;
import com.xiaohe66.web.sys.filter.DevAutoLoginFilter;
import com.xiaohe66.web.sys.filter.TokenLoginFilter;
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

    /**
     * filter ，放在前面的会被先过滤
     */
    @Bean
    public TokenLoginFilter tokenLoginFilter() {
        return new TokenLoginFilter();
    }

    @Bean
    @ConditionalOnProperty(value = "debug", havingValue = "true")
    public DevAutoLoginFilter autoLoginFilter() {
        return new DevAutoLoginFilter();
    }

}
