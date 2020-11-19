package ro.tabletainmultirii.api.repository.dynamodb.schools;

import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter;
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime> {

    @Override
    public AttributeValue transformFrom(LocalDateTime localDateTime) {
        return AttributeValue.builder()
                .s(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime))
                .build();
    }

    @Override
    public LocalDateTime transformTo(AttributeValue attributeValue) {
        return LocalDateTime.parse(attributeValue.s());
    }

    @Override
    public EnhancedType<LocalDateTime> type() {
        return EnhancedType.of(LocalDateTime.class);
    }

    @Override
    public AttributeValueType attributeValueType() {
        return AttributeValueType.S;
    }
}
