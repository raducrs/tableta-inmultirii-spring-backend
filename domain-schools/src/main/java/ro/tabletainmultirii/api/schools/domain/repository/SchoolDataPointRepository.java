package ro.tabletainmultirii.api.schools.domain.repository;

import ro.tabletainmultirii.api.schools.domain.aggregates.SchoolDataPoint;

import java.util.List;

public interface SchoolDataPointRepository {
    SchoolDataPoint getForDate(String formattedDate);
    List<SchoolDataPoint> getAll();
    boolean save(SchoolDataPoint schoolDataPoint);
}
