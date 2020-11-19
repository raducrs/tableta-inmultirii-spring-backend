package ro.tabletainmultirii.api.controllers.schools.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ro.tabletainmultirii.api.controllers.schools.dto.*;
import ro.tabletainmultirii.api.schools.domain.aggregates.SchoolDataPoint;
import ro.tabletainmultirii.api.schools.domain.repository.SchoolDataPointRepository;
import ro.tabletainmultirii.api.schools.domain.services.SchoolDataPointService;
import ro.tabletainmultirii.api.schools.domain.vo.SchoolStatistic;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS) // https://stackoverflow.com/questions/12115996/spring-cache-cacheable-method-ignored-when-called-from-within-the-same-class https://stackoverflow.com/a/34090850/5763690
public class ScenarioDataService {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioDataService.class);

    @Autowired
    private SchoolDataPointRepository repository;

    @Autowired
    private SchoolDataPointService dataPointService;

    @Autowired
    private ScenarioDataService self;

    @Caching(cacheable = {
            @Cacheable(value = "s3schools", key = "'schools/datapoints/history.json'", unless="#result == null")
    })
    public ScenarioData getHistory(){

        logger.warn("Cache miss for schools");

        var points = repository.getAll();

        points.sort(Comparator.comparing(SchoolDataPoint::getDate));
        var lastUpdate = points.stream()
                .filter(Predicate.not(SchoolDataPoint::isDerived))
                .max(Comparator.comparing(SchoolDataPoint::getDate))
                .map(SchoolDataPoint::getSource)
                .orElse(null);


        var statPoints = points.stream().map(point -> {

                var schoolStat = SchoolStatistics.builder()
                        .green(buildSchoolStatistic(point.getGreen()))
                        .yellow(buildSchoolStatistic(point.getYellow()))
                        .red(buildSchoolStatistic(point.getRed()))
                        .redCovid(buildSchoolStatistic(point.getRedCovid()))
                        .redLockdown(buildSchoolStatistic(point.getRedLockdown()))
                .build();
                return StatisticDataPoint.builder()
                        .stats(schoolStat)
                        .date(point.getDate())
                        .source(SourceDTO.from(point.getSource()))
                        .isDerived(point.isDerived())
                        .build();
        }).collect(Collectors.toList());

        return ScenarioData.builder()
                .lastUpdateSource(SourceDTO.from(lastUpdate))
                .data(statPoints)
                .build();
    }

    @Caching(cacheable = {
            @Cacheable(value = "s3schoolslatest", key = "'schools/datapoints/latest.json'", unless="#result == null")
    })
    public StatisticDataPoint getLatest() {

        logger.warn("Cache miss for latest");

        var scenario = self.getHistory();

        if ( scenario == null ){ return null;};
        var lastDate = scenario.getLastUpdateSource().getAccessed().toLocalDate();

        return scenario.getData().stream()
                .filter(p -> p.getDate().isEqual(lastDate))
                .findFirst().orElseGet(() -> scenario.getData().stream()
                        .max(Comparator.comparing(StatisticDataPoint::getDate))
                        .orElse(null));

    }

    private SchoolStatistic buildSchoolStatistic(SchoolStatistic stat) {
        var name = "";
        switch (stat.getName()){
            case "red":
                name = "S1";
                break;
            case "redCovid":
                name = "S1-COVID";
                break;
            case "redLockdown":
                name = "S1-Incidenta";
                break;
            case "yellow":
                name = "S2";
                break;
            case "green":
                name = "S3";
                break;
        }
        return SchoolStatistic.builder()
                .name(name)
                .count(stat.getCount())
                .build();
    }

    public SchoolDataPoint addNew(NewSchoolPoint newSchoolPoint) {
        var result = dataPointService.add(newSchoolPoint.getDate(),newSchoolPoint.getSourceName(), newSchoolPoint.getSourceUrl(),
                newSchoolPoint.getRed(), newSchoolPoint.getYellow(), newSchoolPoint.getGreen(), newSchoolPoint.getRedCovid(), newSchoolPoint.getRedLockdown());
        if ( result != null){
            self.evictCache();
        }
        return result;
    }

    public SchoolDataPoint addMissing(MissingSchoolPoint missingSchoolPoint) {
        var result = dataPointService.addDerived(missingSchoolPoint.getDate());
        if ( result != null){
            self.evictCache();
        }
        return result;
    }

    @Caching(evict = {
            @CacheEvict(value = "s3schools",allEntries = true),
            @CacheEvict(value = "s3schoolslatest", allEntries = true)
    })

    public void evictCache() {
        logger.warn("Cache evicted");
    }

    @Caching(cacheable = {
            @Cacheable(value = "s3schoolspoints", key = "'schools/datapoints/'+#formattedDate+'.json'", unless="#result == null"),
    })
    public SchoolDataPoint getForDate(String formattedDate) {
        return repository.getForDate(formattedDate);
    }
}
