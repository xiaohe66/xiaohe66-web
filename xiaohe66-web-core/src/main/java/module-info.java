module xiaohe.web.core {

    exports com.xiaohe66.web.domain.account.aggregate;
    exports com.xiaohe66.web.domain.account.value;
    exports com.xiaohe66.web.domain.account.repository;
    exports com.xiaohe66.web.domain.account.service;

    exports com.xiaohe66.web.domain.wx.user.aggregate;
    exports com.xiaohe66.web.domain.wx.user.value;
    exports com.xiaohe66.web.domain.wx.user.repository;
    exports com.xiaohe66.web.domain.wx.user.service;

    exports com.xiaohe66.web.domain.sys.sec.aggregate;
    exports com.xiaohe66.web.domain.sys.sec.entity;
    exports com.xiaohe66.web.domain.sys.sec.value;
    exports com.xiaohe66.web.domain.sys.sec.repository;
    exports com.xiaohe66.web.domain.sys.sec.service;

    exports com.xiaohe66.web.domain.task.agg;
    exports com.xiaohe66.web.domain.task.entity;
    exports com.xiaohe66.web.domain.task.value;
    exports com.xiaohe66.web.domain.task.repository;
    exports com.xiaohe66.web.domain.task.service;

    exports com.xiaohe66.web.integration.config;
    exports com.xiaohe66.web.integration.domain;
    exports com.xiaohe66.web.integration.ex;
    exports com.xiaohe66.web.integration.value;

    exports com.xiaohe66.web.infrastructure.acl.wx;
    exports com.xiaohe66.web.infrastructure.acl.wx.model;
    exports com.xiaohe66.web.infrastructure.acl.wx.request;
    exports com.xiaohe66.web.infrastructure.acl.wx.response;

    opens com.xiaohe66.web.domain.account.service;
    opens com.xiaohe66.web.domain.account.repository;

    opens com.xiaohe66.web.domain.task.service;
    opens com.xiaohe66.web.domain.task.repository;

    opens com.xiaohe66.web.domain.wx.user.service;

    opens com.xiaohe66.web.infrastructure.acl.wx;
    opens com.xiaohe66.web.infrastructure.acl.wx.request;

    opens com.xiaohe66.web.integration.config;

    // mapstruct
    requires static java.compiler;
    requires static org.mapstruct;

    requires static lombok;

    requires transitive xiaohe.common.base;
    requires transitive xiaohe.common.api;

    requires transitive org.apache.commons.codec;
    requires transitive org.aspectj.weaver;

    requires transitive spring.aop;
    requires transitive spring.boot;
    requires transitive spring.context;
    requires transitive spring.web;
    requires transitive spring.tx;

}