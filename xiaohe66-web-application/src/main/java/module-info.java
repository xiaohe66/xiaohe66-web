module xiaohe.web.application {

    exports com.xiaohe66.web.application.sys.sec;
    exports com.xiaohe66.web.application.sys.sec.bo;

    exports com.xiaohe66.web.application.todo;
    exports com.xiaohe66.web.application.todo.bo;
    exports com.xiaohe66.web.application.todo.result;

    opens com.xiaohe66.web.application.aop;
    opens com.xiaohe66.web.application.aop.annotation;
    opens com.xiaohe66.web.application.sys.sec.convert;
    opens com.xiaohe66.web.application.todo;
    opens com.xiaohe66.web.application.todo.convert;
    opens com.xiaohe66.web.application.todo.result;

    // mapstruct
    requires static java.compiler;
    requires static org.mapstruct;

    requires static lombok;

    requires transitive xiaohe.web.core;

}