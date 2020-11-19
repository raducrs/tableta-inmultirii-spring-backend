package ro.tabletainmultirii.api.repository.test.schools;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import ro.tabletainmultirii.api.schools.domain.aggregates.SchoolDataPoint;
import ro.tabletainmultirii.api.schools.domain.repository.SchoolDataPointRepository;
import ro.tabletainmultirii.api.schools.domain.vo.SchoolStatistic;
import ro.tabletainmultirii.api.schools.domain.vo.Source;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Profile({"dev", "default"})
public class SchoolDataPointRepositoryImpl implements SchoolDataPointRepository {

    private final Map<String,SchoolDataPoint> inMemory = new HashMap<>();
    {
        inMemory.put("2020-10-10", construct("2020-10-10",100,101,202, 50, 50));
        inMemory.put("2020-10-11", construct("2020-10-11",1102,1101,1202, 50,100));
        inMemory.put("2020-10-12", construct("2020-10-12",2103,2104,2202, 100, 500));
    }

    @Override
    public SchoolDataPoint getForDate(String formattedDate) {
        return inMemory.getOrDefault(formattedDate,null);
    }

    @Override
    public List<SchoolDataPoint> getAll() {
        return new ArrayList<>(inMemory.values());
    }

    @Override
    public boolean save(SchoolDataPoint schoolDataPoint) {
        inMemory.put(schoolDataPoint.getDate().format(DateTimeFormatter.ISO_DATE),schoolDataPoint);
        return true;
    }

    private SchoolDataPoint construct(String formattedDate,int red, int green, int yellow, int redCovid, int redLockdown){
        return SchoolDataPoint.builder()
                .source(Source.builder()
                        .accessed(LocalDate.parse(formattedDate, DateTimeFormatter.ISO_DATE).atTime(13,42))
                        .build())
                .red(SchoolStatistic.builder().count(red).name("red").build())
                .redCovid(SchoolStatistic.builder().count(redCovid).name("redCovid").build())
                .redLockdown(SchoolStatistic.builder().count(redLockdown).name("redLockdown").build())
                .yellow(SchoolStatistic.builder().count(green).name("yellow").build())
                .green(SchoolStatistic.builder().count(yellow).name("green").build())
                .date(LocalDate.parse(formattedDate, DateTimeFormatter.ISO_DATE))
                .build();
    }
}
