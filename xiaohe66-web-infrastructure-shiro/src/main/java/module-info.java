module xiaohe.web.infrastructure.shiro {

    opens com.xiaohe66.web.infrastructure.shiro;

    requires transitive xiaohe.web.core;

    requires static lombok;

    requires transitive shiro.core;
    requires transitive shiro.spring;
    requires transitive shiro.web;

    requires spring.aop;
    requires spring.boot.autoconfigure;
    requires spring.core;

    requires org.apache.tomcat.embed.core;
}