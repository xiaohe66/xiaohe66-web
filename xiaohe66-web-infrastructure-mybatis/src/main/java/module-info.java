open module xiaohe.web.infrastructure.mybatis {

    exports com.xiaohe66.web.integration;

    /*opens com.xiaohe66.web.integration;
    opens com.xiaohe66.web.infrastructure.mybatis.account.convert;
    opens com.xiaohe66.web.infrastructure.mybatis.account.mapper;
    opens com.xiaohe66.web.infrastructure.mybatis.account.model;
    opens com.xiaohe66.web.infrastructure.mybatis.account.repository;

    opens com.xiaohe66.web.infrastructure.mybatis.sys.convert;
    opens com.xiaohe66.web.infrastructure.mybatis.sys.mapper;
    opens com.xiaohe66.web.infrastructure.mybatis.sys.model;
    opens com.xiaohe66.web.infrastructure.mybatis.sys.repository;

    opens com.xiaohe66.web.infrastructure.mybatis.wx.user.convert;
    opens com.xiaohe66.web.infrastructure.mybatis.wx.user.mapper;
    opens com.xiaohe66.web.infrastructure.mybatis.wx.user.model;
    opens com.xiaohe66.web.infrastructure.mybatis.wx.user.repository;*/

    requires transitive xiaohe.web.core;
    //requires transitive xiaohe.crud.core;

    // mapstruct
    requires static java.compiler;
    requires static org.mapstruct;

    requires static lombok;

    requires transitive java.annotation;

    requires transitive spring.beans;
    requires transitive spring.context;

    requires transitive org.mybatis;
    requires transitive mybatis.plus.annotation;
    requires transitive mybatis.plus.core;
    requires transitive mybatis.plus.extension;

}