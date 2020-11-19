package ro.tabletainmultirii.api.repository.dynamodb.schools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ro.tabletainmultirii.api.schools.domain.aggregates.SchoolDataPoint;
import ro.tabletainmultirii.api.schools.domain.repository.SchoolDataPointRepository;
import ro.tabletainmultirii.api.schools.domain.vo.SchoolStatistic;
import ro.tabletainmultirii.api.schools.domain.vo.Source;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Profile({"local", "local-prod"})
public class SchoolDataPointRepositoryImpl implements SchoolDataPointRepository {

    @Autowired
    private StatisticDynDBRepo repo;

    @Override
    public SchoolDataPoint getForDate(String formattedDate) {
        return repo.findByDate(formattedDate)
                .map(getStatisticDynDBSchoolDataPointFunction(formattedDate))
                .orElse(null);
    }


    @Override
    public List<SchoolDataPoint> getAll() {
        return repo.findAll().stream()
                .map(stat -> getStatisticDynDBSchoolDataPointFunction(stat.getDateKey()).apply(stat))
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(SchoolDataPoint schoolDataPoint) {
        return repo.save(
                StatisticDynDB.builder()
                        .sourceUrl(schoolDataPoint.getSource().getUrl())
                        .sourceName(schoolDataPoint.getSource().getName())
                        .dateKey(schoolDataPoint.getDate().format(DateTimeFormatter.ISO_DATE))
                        .accessed(schoolDataPoint.getSource().getAccessed())
                        .green(schoolDataPoint.getGreen().getCount())
                        .yellow(schoolDataPoint.getYellow().getCount())
                        .red(schoolDataPoint.getRed().getCount())
                        .redCovid(schoolDataPoint.getRedCovid().getCount())
                        .redLockdown(schoolDataPoint.getRedLockdown().getCount())
                        .derived(schoolDataPoint.isDerived())
                        .build()
        );
    }

    private Function<StatisticDynDB, SchoolDataPoint> getStatisticDynDBSchoolDataPointFunction(String formattedDate) {
        return stat ->
                SchoolDataPoint.builder()
                        .date(LocalDate.parse(formattedDate, DateTimeFormatter.ISO_DATE))
                        .source(
                                Source.builder()
                                        .name(stat.getSourceName())
                                        .url(stat.getSourceUrl())
                                        .accessed(stat.getAccessed())
                                        .build()
                        )
                        .green(SchoolStatistic.builder()
                                .count(stat.getGreen())
                                .name("green")
                                .build()

                        )
                        .yellow(SchoolStatistic.builder()
                                .count(stat.getYellow())
                                .name("yellow")
                                .build()
                        )
                        .red(SchoolStatistic.builder()
                                .count(stat.getRed())
                                .name("red")
                                .build()
                        ).redCovid(SchoolStatistic.builder()
                                .count(stat.getRedCovid())
                                .name("redCovid")
                                .build()
                        ).redLockdown(SchoolStatistic.builder()
                                .count(stat.getRedLockdown())
                                .name("redLockdown")
                                .build()
                        )
                        .derived(stat.isDerived())
                        .build();
    }
}
