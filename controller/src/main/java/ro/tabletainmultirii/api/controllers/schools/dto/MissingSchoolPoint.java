package ro.tabletainmultirii.api.controllers.schools.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class MissingSchoolPoint {
    private LocalDate date;
}
