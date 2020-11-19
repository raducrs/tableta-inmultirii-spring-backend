package ro.tabletainmultirii.api.repository.dynamodb.stats;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.regions.Region;


@Configuration
@Profile({"local", "local-prod"})
@PropertySource(value = "dynamo_stats_application-${spring.profiles.active}.properties", ignoreResourceNotFound = true)
public class DynamoStatsDBConfig {

    @Value("${amazon.dynamodb.table.stats:http}")
    private String table;

    @Value("${amazon.aws.accesskey.stats:}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey.stats:}")
    private String amazonAWSSecretKey;

    @Bean
    @Qualifier("dynamoDBClientStats")
    public DynamoDbClient dynamoDbClientStats(){

        var cred = StaticCredentialsProvider.create(
                AwsBasicCredentials.create(amazonAWSAccessKey,amazonAWSSecretKey)
        );
        return DynamoDbClient.builder()
                .credentialsProvider(cred)
                .region(Region.EU_CENTRAL_1)
                .build();
    }

    @Bean
    @Qualifier("dynamoDBClientEnhancedStats")
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(@Qualifier("dynamoDBClientStats") DynamoDbClient dynamoDbClient){
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }


    public String getTable() {
        return table;
    }
}
