package com.xiaohe66.web.infrastructure.shiro;

import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.infrastructure.shiro.handler.ShiroFilterInterceptor;
import com.xiaohe66.web.infrastructure.shiro.handler.ShiroFilterIsAccessAllowedHandler;
import com.xiaohe66.web.infrastructure.shiro.handler.ShiroFilterOnAccessDeniedHandler;
import com.xiaohe66.web.infrastructure.shiro.handler.extend.DevAutoLoginExtend;
import com.xiaohe66.web.infrastructure.shiro.realm.XhDefaultRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaohe
 * @time 2019.12.26 18:31
 */
@Configuration
@Slf4j
public class ShiroConfiguration {

    /*@Bean
    public TokenLoginExtend tokenLoginExtend() {
        return new TokenLoginExtend();
    }*/

    @Bean
    @ConditionalOnProperty(value = "autologin", havingValue = "true")
    public DevAutoLoginExtend devAuthLoginExtend(SecurityService securityService) {
        return new DevAutoLoginExtend(securityService);
    }

    @Bean
    public ShiroFilterIsAccessAllowedHandler isAccessAllowedExtend(ApplicationContext context) {

        Map<String, ShiroFilterIsAccessAllowedHandler.Extend> beans = context.getBeansOfType(ShiroFilterIsAccessAllowedHandler.Extend.class);

        List<ShiroFilterIsAccessAllowedHandler.Extend> handlerList = new ArrayList<>(beans.values());

        return new ShiroFilterIsAccessAllowedHandler(handlerList);
    }

    @Bean
    public ShiroFilterOnAccessDeniedHandler onAccessDeniedHandler() {

        return new ShiroFilterOnAccessDeniedHandler();
    }

    @Bean
    public ShiroFilterInterceptor shiroFilterInvocationHandler(ShiroFilterIsAccessAllowedHandler isAccessAllowedHandler) {

        ShiroFilterInterceptor handler = new ShiroFilterInterceptor();
        handler.addExtend("isAccessAllowed", isAccessAllowedHandler);
        handler.addExtend("onAccessDenied", onAccessDeniedHandler());

        return handler;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(ShiroFilterInterceptor shiroFilterInvocationHandler) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        //bean.setLoginUrl("/web/org/user/login.html");
        //bean.setUnauthorizedUrl("/web/org/user/login.html");

        Map<String, Filter> filters = new HashMap<>();

        for (DefaultFilter defaultFilter : DefaultFilter.values()) {

            // cglib 增强，使每个 shiro filter执行前，都调用自定义的方法

            Enhancer enhancer = new Enhancer();

            enhancer.setSuperclass(defaultFilter.getFilterClass());

            enhancer.setCallback(shiroFilterInvocationHandler);

            filters.put(defaultFilter.name(), (Filter) enhancer.create());
        }

        bean.setFilters(filters);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("/**", "anon");

        bean.setFilterChainDefinitionMap(map);

        return bean;
    }

    @Bean
    public XhDefaultRealm myRealm() {
        return new XhDefaultRealm();
    }

    /**
     * 安全管理器
     * 注：使用shiro-spring-boot-starter 1.4时，返回类型是SecurityManager会报错，直接引用shiro-spring则不报错
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        SecurityUtils.setSecurityManager(securityManager);
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

}
