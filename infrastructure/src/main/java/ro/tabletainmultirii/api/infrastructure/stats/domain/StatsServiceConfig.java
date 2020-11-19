package ro.tabletainmultirii.api.infrastructure.stats.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ro.tabletainmultirii.api.stats.domain.repository.GadgetsRepository;

@Configuration
public class StatsServiceConfig {

    @Autowired // introduce bean for higher lever dependants
    private GadgetsRepository repo;
}
