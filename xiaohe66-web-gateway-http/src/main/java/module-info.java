module xiaohe.web.gateway.http {

    requires transitive xiaohe.web.core;

    opens com.xiaohe66.web.gateway.http;
    opens com.xiaohe66.web.gateway.http.sec;

    requires static lombok;
}