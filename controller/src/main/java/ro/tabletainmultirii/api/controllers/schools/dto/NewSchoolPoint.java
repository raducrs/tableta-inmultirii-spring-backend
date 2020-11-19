package ro.tabletainmultirii.api.controllers.schools.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class NewSchoolPoint {
    private LocalDate date;
    private String sourceName;
    private String sourceUrl;
    private int red;
    private int yellow;
    private int green;
    private int redCovid;
    private int redLockdown;
}
