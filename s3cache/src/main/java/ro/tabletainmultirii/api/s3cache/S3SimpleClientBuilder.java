package ro.tabletainmultirii.api.s3cache;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

class S3SimpleClientBuilder {

    static S3Client from(String amazonAWSAccessKey, String amazonAWSSecretKey){

        var cred = StaticCredentialsProvider.create(
                AwsBasicCredentials.create(amazonAWSAccessKey,amazonAWSSecretKey)
        );

        return S3Client.builder()
                .credentialsProvider(cred)
                .region(Region.EU_CENTRAL_1)
                .build();

    }
}
