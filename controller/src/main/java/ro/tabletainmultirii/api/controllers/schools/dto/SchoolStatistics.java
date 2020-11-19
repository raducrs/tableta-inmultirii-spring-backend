package ro.tabletainmultirii.api.controllers.schools.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ro.tabletainmultirii.api.schools.domain.vo.SchoolStatistic;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SchoolStatistics {
    private SchoolStatistic red;
    private SchoolStatistic redCovid;
    private SchoolStatistic redLockdown;
    private SchoolStatistic yellow;
    private SchoolStatistic green;
}
