package ro.tabletainmultirii.api.repository.dynamodb.stats;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDbBean
public class StatDynDB {
    private String Statistic;
    private int count;

    @DynamoDbPartitionKey()
    @DynamoDbAttribute("Statistic")
    public String getStatistic() {
        return Statistic;
    }

    public void setStatistic(String statistic) {
        this.Statistic = statistic;
    }

    @DynamoDbAttribute("Count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
