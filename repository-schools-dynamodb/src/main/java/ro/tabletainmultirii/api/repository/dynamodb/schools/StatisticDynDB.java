package ro.tabletainmultirii.api.repository.dynamodb.schools;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbConvertedBy;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.LocalDateTime;

//@DynamoDBTable(tableName = "TI-All-Schools-Stats")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDbBean
public class StatisticDynDB {
    private String dateKey;
    private String sourceName;
    private String sourceUrl;
    private LocalDateTime accessed;
    private int red;
    private int redCovid;
    private int redLockdown;
    private int yellow;
    private int green;
    private boolean derived;

    @DynamoDbPartitionKey()
    public String getDateKey() {
        return dateKey;
    }

    public void setDateKey(String dateKey) {
        this.dateKey = dateKey;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @DynamoDbAttribute("sourceAccessed")
    @DynamoDbConvertedBy(LocalDateTimeConverter.class)
    public LocalDateTime getAccessed() {
        return accessed;
    }

    public void setAccessed(LocalDateTime accessed) {
        this.accessed = accessed;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getYellow() {
        return yellow;
    }

    public void setYellow(int yellow) {
        this.yellow = yellow;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public boolean isDerived() {
        return derived;
    }

    public void setDerived(boolean derived) {
        this.derived = derived;
    }

    public int getRedCovid() {
        return redCovid;
    }

    public void setRedCovid(int redCovid) {
        this.redCovid = redCovid;
    }

    public int getRedLockdown() {
        return redLockdown;
    }

    public void setRedLockdown(int redLockdown) {
        this.redLockdown = redLockdown;
    }
}
