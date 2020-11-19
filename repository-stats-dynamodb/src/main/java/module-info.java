import ro.tabletainmultirii.api.stats.domain.repository.GadgetsRepository;

module ro.tabletainmultirii.api.repository.dynamodb.stats {
    requires ro.tabletainmultirii.api.stats.domain;

    requires static lombok;

    requires spring.context;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.beans;
    requires software.amazon.awssdk.services.dynamodb;
    requires software.amazon.awssdk.auth;
    requires software.amazon.awssdk.regions;
    requires software.amazon.awssdk.enhanced.dynamodb;
    requires software.amazon.awssdk.core;
    // requires com.fasterxml.classmate;

    opens ro.tabletainmultirii.api.repository.dynamodb.stats to spring.core;

    exports ro.tabletainmultirii.api.repository.dynamodb.stats;

    provides GadgetsRepository with ro.tabletainmultirii.api.repository.dynamodb.stats.GadgetsRepositoryImpl;
}