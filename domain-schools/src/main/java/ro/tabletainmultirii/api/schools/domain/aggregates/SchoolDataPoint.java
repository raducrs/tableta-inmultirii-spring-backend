package ro.tabletainmultirii.api.schools.domain.aggregates;

import lombok.*;
import ro.tabletainmultirii.api.schools.domain.vo.SchoolStatistic;
import ro.tabletainmultirii.api.schools.domain.vo.Source;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class SchoolDataPoint {
    private LocalDate date;
    private Source source;
    private SchoolStatistic red;
    private SchoolStatistic redCovid;
    private SchoolStatistic redLockdown;
    private SchoolStatistic yellow;
    private SchoolStatistic green;
    private boolean derived;
}
