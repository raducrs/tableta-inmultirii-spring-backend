package ro.tabletainmultirii.api.s3cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

public class S3AdapterImpl implements S3Adapter{

    private final S3Client s3;
    private final String bucket;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private S3AdapterImpl(S3Client s3, String bucket) {
        this.s3 = s3;
        this.bucket = bucket;
    }

    @Override
    public boolean save(Object key, Object value) {
        try {

            // only JSON support now
            var bytes = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(value);
            var reqBody = RequestBody.fromBytes(bytes);

            // for mime type
            // https://stackoverflow.com/questions/23467044/how-to-set-the-content-type-of-an-s3-object-via-the-sdk
            // https://medium.com/qacolony/determining-mime-types-in-java-for-aws-s3-1006ab520836

            // TODO should also add gzip compression for fast access
            s3.putObject(PutObjectRequest.builder()
                            .bucket(bucket)
                            .key(key.toString())
                            .build(),
                            reqBody
            );

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static S3Adapter from(String amazonAWSAccessKey, String amazonAWSSecretKey, String bucket){
        var client = S3SimpleClientBuilder.from(amazonAWSAccessKey,amazonAWSSecretKey);
        return new S3AdapterImpl(client,bucket);
    }
}
