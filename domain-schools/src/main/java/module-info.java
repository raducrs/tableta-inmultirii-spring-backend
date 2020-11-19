module ro.tabletainmultirii.api.schools.domain {
    requires static lombok;
    exports ro.tabletainmultirii.api.schools.domain.aggregates;
    exports ro.tabletainmultirii.api.schools.domain.repository;
    exports ro.tabletainmultirii.api.schools.domain.vo;
    exports ro.tabletainmultirii.api.schools.domain.services;
}