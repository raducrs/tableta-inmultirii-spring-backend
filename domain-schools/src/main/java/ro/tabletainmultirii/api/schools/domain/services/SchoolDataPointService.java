package ro.tabletainmultirii.api.schools.domain.services;

import ro.tabletainmultirii.api.schools.domain.aggregates.SchoolDataPoint;

import java.time.LocalDate;

public interface SchoolDataPointService {

    SchoolDataPoint add(LocalDate date, String sourceName, String sourceUrl, int red, int yellow, int green, int redCovid, int redLockdown);
    SchoolDataPoint addDerived(LocalDate date);
}
