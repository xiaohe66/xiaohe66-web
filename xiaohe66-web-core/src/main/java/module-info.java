module xiaohe.web.core {

    exports com.xiaohe66.web.application;
    exports com.xiaohe66.web.application.request;

    exports com.xiaohe66.web.domain.account.aggregate;
    exports com.xiaohe66.web.domain.account.value;
    exports com.xiaohe66.web.domain.account.repository;
    exports com.xiaohe66.web.domain.wx.user.aggregate;
    exports com.xiaohe66.web.domain.wx.user.value;
    exports com.xiaohe66.web.domain.wx.user.repository;

    exports com.xiaohe66.web.domain.sys.sec.aggregate;
    exports com.xiaohe66.web.domain.sys.sec.entity;
    exports com.xiaohe66.web.domain.sys.sec.ex;
    exports com.xiaohe66.web.domain.sys.sec.value;
    exports com.xiaohe66.web.domain.sys.sec.repository;
    exports com.xiaohe66.web.domain.sys.sec.service;

    exports com.xiaohe66.web.integration.domain;
    exports com.xiaohe66.web.integration.value;

    opens com.xiaohe66.web.application;
    opens com.xiaohe66.web.application.config;
    opens com.xiaohe66.web.application.convert;

    opens com.xiaohe66.web.domain.account.service;
    opens com.xiaohe66.web.domain.account.repository;

    opens com.xiaohe66.web.infrastructure.acl.wx;
    opens com.xiaohe66.web.infrastructure.acl.wx.request;

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