package ro.tabletainmultirii.api.controllers.stats.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ro.tabletainmultirii.api.stats.domain.repository.GadgetsRepository;
import ro.tabletainmultirii.api.controllers.stats.dto.GadgetsStats;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS) // https://stackoverflow.com/questions/12115996/spring-cache-cacheable-method-ignored-when-called-from-within-the-same-class https://stackoverflow.com/a/34090850/5763690
public class StatsService {

    private static final Logger logger = LoggerFactory.getLogger(StatsService.class);

    @Autowired
    private GadgetsRepository repository;

    @Autowired
    private StatsService self;

    @Caching(cacheable = {
            @Cacheable(value = "s3stats", key = "'stats/gadgets.json'", unless="#result == null")
    })
    public GadgetsStats getGadgetsStats() {

        logger.warn("Cache miss for gadgets");

        var points = repository.getCurrent();
        return GadgetsStats.builder()
                .l(points.getLaptops().getCount())
                .t(points.getTablets().getCount())
                .p(points.getPhones().getCount())
                .build();
    }

    @Caching(evict = {
            @CacheEvict(value = "s3stats",allEntries = true),
    })

    public void evictCache() {
        logger.warn("Cache gadgets evicted");
    }

}
