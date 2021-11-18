module xiaohe.web.core {

    //exports com.xiaohe66.web.application;
    exports com.xiaohe66.web.application.sys.sec;
    exports com.xiaohe66.web.application.sys.sec.bo;

    exports com.xiaohe66.web.application.todo;
    exports com.xiaohe66.web.application.todo.bo;
    exports com.xiaohe66.web.application.todo.result;

    exports com.xiaohe66.web.domain.account.aggregate;
    exports com.xiaohe66.web.domain.account.value;
    exports com.xiaohe66.web.domain.account.repository;
    exports com.xiaohe66.web.domain.wx.user.aggregate;
    exports com.xiaohe66.web.domain.wx.user.value;
    exports com.xiaohe66.web.domain.wx.user.repository;

    exports com.xiaohe66.web.domain.sys.sec.aggregate;
    exports com.xiaohe66.web.domain.sys.sec.entity;
    exports com.xiaohe66.web.domain.sys.sec.value;
    exports com.xiaohe66.web.domain.sys.sec.repository;
    exports com.xiaohe66.web.domain.sys.sec.service;

    exports com.xiaohe66.web.domain.todo.agg;
    exports com.xiaohe66.web.domain.todo.value;
    exports com.xiaohe66.web.domain.todo.repository;
    exports com.xiaohe66.web.domain.todo.service;

    exports com.xiaohe66.web.integration.domain;
    exports com.xiaohe66.web.integration.ex;
    exports com.xiaohe66.web.integration.value;

    opens com.xiaohe66.web.application.sys.sec.convert;
    opens com.xiaohe66.web.application.todo.convert;
    opens com.xiaohe66.web.application.todo.result;

    opens com.xiaohe66.web.domain.account.service;
    opens com.xiaohe66.web.domain.account.repository;

    opens com.xiaohe66.web.domain.todo.service;
    opens com.xiaohe66.web.domain.todo.repository;

    opens com.xiaohe66.web.domain.wx.user.service;

    opens com.xiaohe66.web.infrastructure.acl.wx;
    opens com.xiaohe66.web.infrastructure.acl.wx.request;

    opens com.xiaohe66.web.integration.config;

    requires transitive xiaohe.common.base;
    requires transitive xiaohe.common.api;

    requires transitive org.apache.commons.codec;

    // mapstruct
    requires static java.compiler;
    requires static org.mapstruct;

    requires static lombok;
    requires transitive spring.context;
    requires transitive spring.web;
    requires transitive spring.boot;

}