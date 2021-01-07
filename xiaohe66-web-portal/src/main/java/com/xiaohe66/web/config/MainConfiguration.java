package com.xiaohe66.web.config;

import com.xiaohe66.web.base.holder.ApplicationContextHolder;
import com.xiaohe66.web.sys.filter.DevAutoLoginFilter;
import com.xiaohe66.web.sys.filter.TokenLoginFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
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
    public FilterRegistrationBean<TokenLoginFilter> tokenLoginFilterBean() {

        FilterRegistrationBean<TokenLoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(tokenLoginFilter());

        registrationBean.addUrlPatterns("/wx/*");

        registrationBean.setName("tokenLoginFilter");

        // 指定过渡器的顺序
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public TokenLoginFilter tokenLoginFilter(){
        return new TokenLoginFilter();
    }

    @Bean
    @ConditionalOnProperty(value = "debug", havingValue = "true")
    public FilterRegistrationBean<DevAutoLoginFilter> autoLoginFilter() {

        FilterRegistrationBean<DevAutoLoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(devAutoLoginFilter());

        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("devAutoLoginFilter");

        // 指定过渡器的顺序
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public DevAutoLoginFilter devAutoLoginFilter(){
        return new DevAutoLoginFilter();
    }

}
