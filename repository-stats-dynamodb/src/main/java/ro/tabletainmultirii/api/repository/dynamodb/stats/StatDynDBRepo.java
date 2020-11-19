package ro.tabletainmultirii.api.repository.dynamodb.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@Component
@Profile({"local", "local-prod"})
public class StatDynDBRepo {
    @Autowired
    @Qualifier("dynamoDBClientEnhancedStats")
    private DynamoDbEnhancedClient enhancedClient;
    @Autowired
    DynamoStatsDBConfig config;

     StatDynDB findByName(String name){
        try {
            // Create a DynamoDbTable object
            DynamoDbTable<StatDynDB> mappedTable = enhancedClient.table(config.getTable(), TableSchema.fromBean(StatDynDB.class));

            // Create a KEY object
            Key key = Key.builder()
                    .partitionValue(name)
                    .build();

            // Get the item by using the key
            StatDynDB result = mappedTable.getItem(r->r.key(key));
            return result;

        } catch (DynamoDbException e) {
            e.printStackTrace();
            return null;
        }
    }


}
