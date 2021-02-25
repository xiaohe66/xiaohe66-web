package com.xiaohe66.web.config;

import com.xiaohe66.web.base.holder.ApplicationContextHolder;
import com.xiaohe66.web.base.holder.WxAccessTokenHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author xiaohe
 * @time 2019.10.12 17:05
 */
@Configuration
@Import(WxAccessTokenHolder.class)
public class MainConfiguration {

    @Bean
    public ApplicationContextHolder applicationContextHolder() {
        return new ApplicationContextHolder();
    }

}
