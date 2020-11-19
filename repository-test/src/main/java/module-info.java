
import ro.tabletainmultirii.api.repository.test.schools.SchoolDataPointRepositoryImpl;
import ro.tabletainmultirii.api.repository.test.stats.GadgetsRepositoryImpl;

module ro.tabletainmultirii.schools.repository.test {
    requires ro.tabletainmultirii.api.schools.domain;
    requires ro.tabletainmultirii.api.stats.domain;

    requires spring.context;

    exports ro.tabletainmultirii.api.repository.test.schools;
    exports ro.tabletainmultirii.api.repository.test.stats;

    provides ro.tabletainmultirii.api.schools.domain.repository.SchoolDataPointRepository with SchoolDataPointRepositoryImpl;
    provides ro.tabletainmultirii.api.stats.domain.repository.GadgetsRepository with GadgetsRepositoryImpl;
}