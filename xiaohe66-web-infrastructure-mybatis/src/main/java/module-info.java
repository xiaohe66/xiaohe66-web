open module xiaohe.web.infrastructure.mybatis {

    // 用于指定 启动类，指定 IBaseMapper
    exports com.xiaohe66.web.integration;

    requires transitive xiaohe.web.core;
    requires transitive xiaohe.web.infrastructure.domain.adapter;

    // mapstruct
    requires static java.compiler;
    requires static org.mapstruct;

    requires static lombok;

    requires transitive org.apache.commons.io;

    requires transitive java.annotation;

    requires transitive spring.beans;
    requires transitive spring.context;

    requires transitive org.mybatis;
    requires transitive mybatis.plus.annotation;
    requires transitive mybatis.plus.core;
    requires transitive mybatis.plus.extension;
    requires xiaohe.common.web.boot.starter;

}