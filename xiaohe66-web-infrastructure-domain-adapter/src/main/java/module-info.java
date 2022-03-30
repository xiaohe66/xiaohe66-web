module xiaohe.web.infrastructure.domain.adapter {

    exports com.xiaohe66.web.infrastructure.domain.adapter;
    exports com.xiaohe66.web.infrastructure.domain.adapter.account;
    exports com.xiaohe66.web.infrastructure.domain.adapter.love;
    exports com.xiaohe66.web.infrastructure.domain.adapter.file;
    exports com.xiaohe66.web.infrastructure.domain.adapter.sec;
    exports com.xiaohe66.web.infrastructure.domain.adapter.task;
    exports com.xiaohe66.web.infrastructure.domain.adapter.wx;

    opens com.xiaohe66.web.infrastructure.domain.adapter;
    opens com.xiaohe66.web.infrastructure.domain.adapter.account;
    opens com.xiaohe66.web.infrastructure.domain.adapter.love;
    opens com.xiaohe66.web.infrastructure.domain.adapter.file;
    opens com.xiaohe66.web.infrastructure.domain.adapter.sec;
    opens com.xiaohe66.web.infrastructure.domain.adapter.task;
    opens com.xiaohe66.web.infrastructure.domain.adapter.wx;

    requires transitive xiaohe.web.core;

}