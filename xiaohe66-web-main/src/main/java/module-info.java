module xiaohe.web.main {

    opens com.xiaohe66.web;

    requires xiaohe.web.core;
    requires xiaohe.web.gateway.http;
    requires xiaohe.web.infrastructure.mybatis;
    requires xiaohe.web.infrastructure.shiro;

    // 解决 mybatis报 Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required
    requires com.zaxxer.hikari;

    // 解决启动时闪退，则需要添加 tomcat
    requires org.apache.tomcat.embed.websocket;

    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires org.mybatis.spring;

    requires static lombok;
    //requires org.mybatis;
    //requires org.mybatis.spring;
}