package ro.tabletainmultirii.api.s3cache;

import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.core.serializer.support.SerializationDelegate;

import java.util.concurrent.ConcurrentMap;

public class S3Cache extends ConcurrentMapCache {

    private S3Adapter s3Adapter;

    public S3Cache(String name) {
        super(name);
    }

    public S3Cache(String name, boolean allowNullValues) {
        super(name, allowNullValues);
    }

    public S3Cache(String name, ConcurrentMap<Object, Object> store, boolean allowNullValues) {
        super(name, store, allowNullValues);
    }

    protected S3Cache(String name, ConcurrentMap<Object, Object> store, boolean allowNullValues, SerializationDelegate serialization) {
        super(name, store, allowNullValues, serialization);
    }

    public Cache setS3Adapter(S3Adapter s3Adapter) {
        this.s3Adapter = s3Adapter;
        return this;
    }

    @Override
    public void put(Object key, Object value) {
        super.put(key, value);
        // do not block http request with s3 operation
        new Thread(() -> {
            s3Adapter.save(key, value);
        }).start();

    }
}
