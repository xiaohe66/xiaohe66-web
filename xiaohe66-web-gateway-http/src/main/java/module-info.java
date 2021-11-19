open module xiaohe.web.gateway.http {

    requires transitive xiaohe.web.application;
    requires transitive java.validation;
    requires transitive com.fasterxml.classmate;

    requires static lombok;

    // mapstruct
    requires static java.compiler;
    requires static org.mapstruct;
}