module xiaohe.web.application {

    opens com.xiaohe66.web.application.aop;
    opens com.xiaohe66.web.application.aop.annotation;
    opens com.xiaohe66.web.application.sys.sec.convert;

    opens com.xiaohe66.web.application.file;
    opens com.xiaohe66.web.application.file.convert;

    opens com.xiaohe66.web.application.task;
    opens com.xiaohe66.web.application.task.convert;
    opens com.xiaohe66.web.application.task.result;

    opens com.xiaohe66.web.application.love;
    opens com.xiaohe66.web.application.love.convert;
    opens com.xiaohe66.web.application.love.result;


    exports com.xiaohe66.web.application.file;
    exports com.xiaohe66.web.application.file.bo;

    exports com.xiaohe66.web.application.love;
    exports com.xiaohe66.web.application.love.bo;
    exports com.xiaohe66.web.application.love.result;

    exports com.xiaohe66.web.application.sys.sec;
    exports com.xiaohe66.web.application.sys.sec.bo;

    exports com.xiaohe66.web.application.task;
    exports com.xiaohe66.web.application.task.bo;
    exports com.xiaohe66.web.application.task.result;


    // mapstruct
    requires static java.compiler;
    requires static org.mapstruct;

    requires static lombok;

    requires transitive org.apache.commons.io;
    requires transitive xiaohe.web.core;

}