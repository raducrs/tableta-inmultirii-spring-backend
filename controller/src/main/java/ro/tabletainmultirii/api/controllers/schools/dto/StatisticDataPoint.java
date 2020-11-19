package ro.tabletainmultirii.api.controllers.schools.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ro.tabletainmultirii.api.controllers.schools.dto.serializers.LocalDateSerializer;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class StatisticDataPoint {
    private SchoolStatistics stats;
    private SourceDTO source;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    private boolean isDerived;
}
