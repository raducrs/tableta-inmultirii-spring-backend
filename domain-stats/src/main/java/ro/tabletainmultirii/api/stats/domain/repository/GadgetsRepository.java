package ro.tabletainmultirii.api.stats.domain.repository;

import ro.tabletainmultirii.api.stats.domain.aggregates.Gadgets;

public interface GadgetsRepository {
    Gadgets getCurrent();
}
