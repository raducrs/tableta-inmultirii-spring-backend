package ro.tabletainmultirii.api.controllers.schools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.tabletainmultirii.api.controllers.schools.adapter.ScenarioDataService;
import ro.tabletainmultirii.api.controllers.schools.dto.MissingSchoolPoint;
import ro.tabletainmultirii.api.controllers.schools.dto.NewSchoolPoint;
import ro.tabletainmultirii.api.controllers.schools.dto.ScenarioData;
import ro.tabletainmultirii.api.controllers.schools.dto.StatisticDataPoint;
import ro.tabletainmultirii.api.schools.domain.aggregates.SchoolDataPoint;
import ro.tabletainmultirii.api.schools.domain.repository.SchoolDataPointRepository;


@RestController
@RequestMapping("/schools/datapoints")
public class SchoolsController {

    @Autowired
    private SchoolDataPointRepository repository;

    @Autowired
    private ScenarioDataService service;

    @GetMapping("/{date}")
    public SchoolDataPoint getForDate(@PathVariable("date") String formattedDate){
        return service.getForDate(formattedDate);
    }

    @GetMapping("/history")
    public ScenarioData getHistory(){
        return service.getHistory();
    }

    @GetMapping("/latest")
    public StatisticDataPoint getLatest(){
        return service.getLatest();
    }


    @PostMapping("/")
    public SchoolDataPoint addNew(@RequestBody() NewSchoolPoint newSchoolPoint){
        return service.addNew(newSchoolPoint);
    }

    @PostMapping("/addMissing")
    public SchoolDataPoint addMissing(@RequestBody() MissingSchoolPoint missingSchoolPoint){
        return service.addMissing(missingSchoolPoint);
    }
}
