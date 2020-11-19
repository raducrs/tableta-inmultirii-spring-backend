package ro.tabletainmultirii.api.controllers.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import ro.tabletainmultirii.api.s3cache.S3Adapter;
import ro.tabletainmultirii.api.s3cache.S3AdapterImpl;
import ro.tabletainmultirii.api.s3cache.S3DummyAdapter;

import java.util.List;

@Configuration
@EnableCaching
@PropertySource(value = { "cache.properties", "cache-${spring.profiles.active}.properties"}, ignoreResourceNotFound = true)
public class CacheConfiguration {

    @Value("${amazon.s3.bucket:empty}")
    private String bucket;

    @Value("${amazon.aws.accesskey:missing}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey:missing}")
    private String amazonAWSSecretKey;

    @Bean
    @Profile({"dev", "default"})
    public S3Adapter s3AdapterDev(){
        return new S3DummyAdapter();
    }


    @Bean
    @Profile({"local","local-prod"})
    public S3Adapter s3Adapter(){
        return S3AdapterImpl.from(amazonAWSAccessKey,amazonAWSSecretKey, bucket);
    }

    @Bean
    public CacheManager cacheManager(List<Cache> cacheList) {
        var cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(cacheList);
        return cacheManager;
    }
}
