package ro.tabletainmultirii.api.controllers.stats.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ro.tabletainmultirii.api.controllers.stats.adapter.StatsService;
import ro.tabletainmultirii.api.s3cache.S3Adapter;
import ro.tabletainmultirii.api.s3cache.S3Cache;

@Configuration
@EnableScheduling
public class CacheStatsConfiguration {

    @Autowired
    private StatsService statsService;

    @Bean
    public Cache s3stats(S3Adapter s3Adapter){
        return  new S3Cache("s3stats").setS3Adapter(s3Adapter);
    }


    // TODO change to https://github.com/ben-manes/caffeine implementation the S3Cache class
    // Solution https://stackoverflow.com/a/38261760/5763690
    @Scheduled(fixedDelay = 12 * 60 * 60 * 1000 ,  initialDelay = 500)
    public void reportCacheEvict() {
       statsService.evictCache();
    }
}
