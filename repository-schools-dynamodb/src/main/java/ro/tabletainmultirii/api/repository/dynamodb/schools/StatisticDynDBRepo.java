package ro.tabletainmultirii.api.repository.dynamodb.schools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.*;


import java.util.stream.Collectors;

@Component
@Profile({"local", "local-prod"})
public class StatisticDynDBRepo{
    @Autowired
    @Qualifier("dynamoDbClientEnhancedSchools")
    private DynamoDbEnhancedClient enhancedClient;
    @Autowired
    DynamoDBSchoolsConfig config;

    Optional<StatisticDynDB> findByDate(String date){
        try {
            // Create a DynamoDbTable object
            DynamoDbTable<StatisticDynDB> mappedTable = enhancedClient.table(config.getTable(), TableSchema.fromBean(StatisticDynDB.class));

            // Create a KEY object
            Key key = Key.builder()
                    .partitionValue(date)
                    .build();

            // Get the item by using the key
            StatisticDynDB result = mappedTable.getItem(r->r.key(key));
            return Optional.ofNullable(result);

        } catch (DynamoDbException e) {
            e.printStackTrace();
            return Optional.empty();
        }

    };
    List<StatisticDynDB> findAll(){
        try{
            // Create a DynamoDbTable object
            DynamoDbTable<StatisticDynDB> custTable = enhancedClient.table(config.getTable(), TableSchema.fromBean(StatisticDynDB.class));

            var items = custTable.scan().items().stream().collect(Collectors.toList());
            return items;
            //return StreamSupport.stream(Spliterators.spliteratorUnknownSize(custTable.scan().items().iterator(),0),false)
            //        .collect(Collectors.toList());

        } catch (DynamoDbException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    };

    boolean save(StatisticDynDB statDynDB){
        try{
            // Create a DynamoDbTable object
            DynamoDbTable<StatisticDynDB> custTable = enhancedClient.table(config.getTable(), TableSchema.fromBean(StatisticDynDB.class));

            custTable.putItem(statDynDB);

            return true;

        } catch (DynamoDbException e) {
            e.printStackTrace();
            return false;
        }
    }




}
