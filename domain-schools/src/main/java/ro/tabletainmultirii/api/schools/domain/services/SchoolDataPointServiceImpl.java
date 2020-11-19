package ro.tabletainmultirii.api.schools.domain.services;


import lombok.AllArgsConstructor;
import ro.tabletainmultirii.api.schools.domain.aggregates.SchoolDataPoint;
import ro.tabletainmultirii.api.schools.domain.repository.SchoolDataPointRepository;
import ro.tabletainmultirii.api.schools.domain.vo.SchoolStatistic;
import ro.tabletainmultirii.api.schools.domain.vo.Source;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

@AllArgsConstructor
public class SchoolDataPointServiceImpl implements SchoolDataPointService {
    private SchoolDataPointRepository repository;
    private SchoolDataPointRepository repo;
    private SchoolDataPoint lastSeen;

    public SchoolDataPointServiceImpl(SchoolDataPointRepository repo) {
        this.repo = repo;
    }

    @Override
    public SchoolDataPoint add(LocalDate date, String sourceName, String sourceUrl, int red, int yellow, int green, int redCovid, int redLockdown){
        var lastDateSeen = getLastSeen();
        if (lastDateSeen != null){
            lastDateSeen.getDate().datesUntil(date).skip(1)
                    .forEach(d ->addDate(d, sourceName, sourceUrl,  red,  yellow,  green, true, redCovid, redLockdown));
        }
        var point = addDate(date, sourceName, sourceUrl,  red,  yellow,  green, false, redCovid, redLockdown);
        if (point != null) { this.lastSeen = point; }
        return point;
    }

    @Override
    public SchoolDataPoint addDerived(LocalDate date) {
        var lastDateSeen = getLastSeen();
        if (lastDateSeen != null){
            lastDateSeen.getDate().datesUntil(date).skip(1)
                    .forEach(d ->{
                        var point = addDate(d, lastDateSeen.getSource().getName(), lastDateSeen.getSource().getUrl(),
                                lastDateSeen.getRed().getCount(),  lastDateSeen.getYellow().getCount(),  lastDateSeen.getGreen().getCount(), true,
                                lastDateSeen.getRedCovid().getCount(), lastSeen.getRedLockdown().getCount());
                        updateLastSeen(point);

                    });
        }
        return this.lastSeen;
    }

    private void updateLastSeen(SchoolDataPoint schoolDataPoint){
        if (schoolDataPoint != null){
            this.lastSeen = schoolDataPoint;
        }
    }

    private SchoolDataPoint addDate(LocalDate date, String sourceName, String sourceUrl, int red, int yellow, int green, boolean derived, int redCovid, int redLockdown){
        var point = SchoolDataPoint.builder()
                .source(Source.builder()
                        .accessed(LocalDateTime.now())
                        .url(sourceUrl)
                        .name(sourceName)
                        .build())
                .green(SchoolStatistic.builder()
                        .name("green")
                        .count(green)
                        .build())
                .yellow(SchoolStatistic.builder()
                        .name("yellow")
                        .count(yellow)
                        .build())
                .red(SchoolStatistic.builder()
                        .name("red")
                        .count(red)
                        .build())
                .redCovid(SchoolStatistic.builder()
                        .name("redCovid")
                        .count(redCovid)
                        .build())
                .redLockdown(SchoolStatistic.builder()
                        .name("redLockdown")
                        .count(redLockdown)
                        .build())
                .date(date)
                .derived(derived)
                .build();
        var success = repo.save(point);
        if (success){
            return point;
        } else {
            return null;
        }
    }

    private SchoolDataPoint getLastSeen(){
        if (lastSeen == null){
            lastSeen= repo.getAll().stream()
                    .max(Comparator.comparing(SchoolDataPoint::getDate))
                    .orElse(null);
        }
        return lastSeen;
    }
}
