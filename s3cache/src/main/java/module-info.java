module ro.tableta.inmultirii.api.s3cache {
    requires org.slf4j;
    requires spring.context;
    requires spring.core;
    requires software.amazon.awssdk.services.s3;
    requires software.amazon.awssdk.core;
    requires com.fasterxml.jackson.databind;
    requires software.amazon.awssdk.auth;
    requires software.amazon.awssdk.regions;

    exports ro.tabletainmultirii.api.s3cache;

}