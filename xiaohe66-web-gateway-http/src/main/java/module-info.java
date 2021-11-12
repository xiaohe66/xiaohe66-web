module xiaohe.web.gateway.http {

    requires transitive xiaohe.web.core;
    requires transitive java.validation;
    requires transitive com.fasterxml.classmate;

    opens com.xiaohe66.web.gateway.http;
    opens com.xiaohe66.web.gateway.http.sec;

    requires static lombok;
}