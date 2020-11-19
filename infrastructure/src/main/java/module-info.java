import ro.tabletainmultirii.api.schools.domain.repository.SchoolDataPointRepository;
import ro.tabletainmultirii.api.stats.domain.repository.GadgetsRepository;

module ro.tabletainmultirii.api.infrastructure {
    requires ro.tabletainmultirii.api.schools.domain;
    requires ro.tabletainmultirii.api.stats.domain;
    //requires static ro.tabletainmultirii.schools.repository;
    //requires static ro.tabletainmultirii.schools.repository.dynamo.db;
    requires transitive ro.tabletainmultirii.api.repository.dynamodb.stats;

    requires spring.beans;
    requires spring.context;

   opens ro.tabletainmultirii.api.infrastructure.schools.domain to spring.core;

   opens ro.tabletainmultirii.api.infrastructure.stats.domain to spring.core;

   exports ro.tabletainmultirii.api.infrastructure.schools.domain to spring.beans, spring.context;

   exports ro.tabletainmultirii.api.infrastructure.stats.domain to spring.beans, spring.context;

   uses SchoolDataPointRepository;
   uses GadgetsRepository;
}