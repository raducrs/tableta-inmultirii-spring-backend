module ro.tabletainmultirii.api.application {
    requires ro.tableta.inmultirii.api.controller;
    requires ro.tableta.inmultirii.api.security;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires org.slf4j;

    requires java.instrument;
    requires com.fasterxml.jackson.databind;
    requires spring.beans;
    requires spring.context;



    // requires jdk.unsupported;

    opens ro.tabletainmultirii.schools.application to spring.core, spring.beans, spring.context;

    exports ro.tabletainmultirii.schools.application;
}