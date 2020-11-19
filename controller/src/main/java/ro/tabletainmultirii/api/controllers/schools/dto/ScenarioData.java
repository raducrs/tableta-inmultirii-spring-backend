package ro.tabletainmultirii.api.controllers.schools.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ScenarioData {
    private SourceDTO lastUpdateSource;
    private List<StatisticDataPoint> data;
}
