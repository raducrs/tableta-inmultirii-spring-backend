import ro.tabletainmultirii.api.schools.domain.repository.SchoolDataPointRepository;
import ro.tabletainmultirii.api.stats.domain.repository.GadgetsRepository;

module ro.tableta.inmultirii.api.controller {
    requires ro.tabletainmultirii.api.schools.domain;
    requires ro.tabletainmultirii.api.stats.domain;
    requires ro.tabletainmultirii.api.infrastructure;
    requires ro.tableta.inmultirii.api.s3cache;

    requires static lombok;
    requires org.slf4j;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.web;

    requires org.apache.tomcat.embed.core;
    requires com.fasterxml.jackson.databind;

    opens ro.tabletainmultirii.api.controllers.schools to spring.core;
    opens ro.tabletainmultirii.api.controllers.schools.adapter to spring.core;
    opens ro.tabletainmultirii.api.controllers.schools.configuration to spring.core;
    opens ro.tabletainmultirii.api.controllers.schools.dto to com.fasterxml.jackson.databind;

    opens ro.tabletainmultirii.api.controllers.stats to spring.core;
    opens ro.tabletainmultirii.api.controllers.stats.adapter to spring.core;
    opens ro.tabletainmultirii.api.controllers.stats.configuration to spring.core;
    opens ro.tabletainmultirii.api.controllers.stats.dto to com.fasterxml.jackson.databind;

    opens ro.tabletainmultirii.api.controllers.configuration to spring.core;

    exports ro.tabletainmultirii.api.controllers.schools;
    exports ro.tabletainmultirii.api.controllers.schools.adapter to spring.beans;
    exports ro.tabletainmultirii.api.controllers.schools.configuration to spring.beans, spring.context;
    exports ro.tabletainmultirii.api.controllers.schools.dto to com.fasterxml.jackson.databind;
    exports ro.tabletainmultirii.api.controllers.schools.dto.serializers to spring.beans, com.fasterxml.jackson.databind;

    exports ro.tabletainmultirii.api.controllers.stats;
    exports ro.tabletainmultirii.api.controllers.stats.adapter to spring.beans;
    exports ro.tabletainmultirii.api.controllers.stats.configuration to spring.beans, spring.context;
    exports ro.tabletainmultirii.api.controllers.stats.dto to com.fasterxml.jackson.databind;

    exports ro.tabletainmultirii.api.controllers.configuration to spring.beans, spring.context;

    uses SchoolDataPointRepository;
    uses GadgetsRepository;

}