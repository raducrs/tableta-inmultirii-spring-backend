package ro.tabletainmultirii.api.s3cache;

public interface S3Adapter {
    boolean save(Object key,Object value);
}
