import ro.tabletainmultirii.api.repository.dynamodb.schools.SchoolDataPointRepositoryImpl;
import ro.tabletainmultirii.api.schools.domain.repository.SchoolDataPointRepository;

module ro.tabletainmultirii.api.repository.dynamodb.schools {
    requires ro.tabletainmultirii.api.schools.domain;

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

    opens ro.tabletainmultirii.api.repository.dynamodb.schools to spring.core;

    exports ro.tabletainmultirii.api.repository.dynamodb.schools;

    provides SchoolDataPointRepository with SchoolDataPointRepositoryImpl;
}