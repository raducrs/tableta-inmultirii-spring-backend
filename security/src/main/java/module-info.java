module ro.tableta.inmultirii.api.security {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.security.config;
    requires spring.security.core;
    requires spring.security.web;
    requires org.apache.tomcat.embed.core;
    requires spring.web;

    opens ro.tabletainmultirii.api.security to spring.core;

    exports ro.tabletainmultirii.api.security to spring.beans, spring.context;
}