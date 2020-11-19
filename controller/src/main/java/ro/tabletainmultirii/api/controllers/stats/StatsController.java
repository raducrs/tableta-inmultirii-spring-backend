package ro.tabletainmultirii.api.controllers.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.tabletainmultirii.api.controllers.stats.adapter.StatsService;
import ro.tabletainmultirii.api.controllers.stats.dto.GadgetsStats;


@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private StatsService service;

    @GetMapping("/gadgets")
    public GadgetsStats getForDate(){
        return service.getGadgetsStats();
    }

    @PutMapping("/gadgets")
    public void addMissing(){
        service.evictCache();
    }
}
