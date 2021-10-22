module xiaohe.web.core {

    exports com.xiaohe66.web.integration.domain;

    exports com.xiaohe66.web.domain.account.aggregate;
    exports com.xiaohe66.web.domain.account.value;
    exports com.xiaohe66.web.domain.account.repository;

    opens com.xiaohe66.web.gateway.http;
    opens com.xiaohe66.web.application;

    opens com.xiaohe66.web.domain.account.service;
    opens com.xiaohe66.web.domain.account.repository;

    requires transitive xiaohe.common.base;

    requires static lombok;
    requires transitive spring.context;
    requires transitive spring.web;


}