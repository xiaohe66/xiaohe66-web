module xiaohe.web.infrastructure.mybatis {

    exports com.xiaohe66.web.integration;

    opens com.xiaohe66.web.integration;
    opens com.xiaohe66.web.infrastructure.mybatis.account.mapper;
    opens com.xiaohe66.web.infrastructure.mybatis.account.model;
    opens com.xiaohe66.web.infrastructure.mybatis.account.repository;

    opens com.xiaohe66.web.infrastructure.mybatis.account.convert;

    requires transitive xiaohe.web.core;
    //requires transitive xiaohe.crud.core;

    // mapstruct
    requires static java.compiler;
    requires static org.mapstruct;

    requires static lombok;

    requires transitive java.annotation;

    requires transitive spring.beans;
    requires transitive spring.context;

    requires transitive mybatis.plus.annotation;
    requires transitive mybatis.plus.core;
    requires transitive mybatis.plus.extension;

}