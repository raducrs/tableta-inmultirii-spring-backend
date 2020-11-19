package ro.tabletainmultirii.api.controllers.schools.configuration;

import org.springframework.cache.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.tabletainmultirii.api.s3cache.S3Adapter;
import ro.tabletainmultirii.api.s3cache.S3Cache;

@Configuration
public class CacheSchoolsConfiguration {

    @Bean
    public Cache s3Schools(S3Adapter s3Adapter){
        return new S3Cache("s3schools").setS3Adapter(s3Adapter);
    }

    @Bean
    public Cache s3schoolslatest(S3Adapter s3Adapter){
        return new S3Cache("s3schoolslatest").setS3Adapter(s3Adapter);
    }

    @Bean
    public Cache s3schoolspoints(S3Adapter s3Adapter){
        return new S3Cache("s3schoolspoints").setS3Adapter(s3Adapter);
    }

}
