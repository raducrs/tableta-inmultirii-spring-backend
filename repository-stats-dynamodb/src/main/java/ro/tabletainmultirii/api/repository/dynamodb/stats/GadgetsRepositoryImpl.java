package ro.tabletainmultirii.api.repository.dynamodb.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ro.tabletainmultirii.api.stats.domain.aggregates.Gadgets;
import ro.tabletainmultirii.api.stats.domain.repository.GadgetsRepository;
import ro.tabletainmultirii.api.stats.domain.vo.GadgetInfo;

@Service
@Profile({"local", "local-prod"})
public class GadgetsRepositoryImpl implements GadgetsRepository {

    @Autowired
    private StatDynDBRepo repo;

    @Override
    public Gadgets getCurrent() {
        var laptopStatDyn = repo.findByName("laptopNumbers");
        var laptop = GadgetInfo.builder()
                .count(laptopStatDyn.getCount())
                .build();
        var tabletStatDyn = repo.findByName("tabletNumbers");
        var tablet = GadgetInfo.builder()
                .count(tabletStatDyn.getCount())
                .build();
        var phoneStatDyn = repo.findByName("phoneNumbers");
        var phone = GadgetInfo.builder()
                .count(phoneStatDyn.getCount())
                .build();
        return Gadgets.builder()
                .laptops(laptop)
                .tablets(tablet)
                .phones(phone)
                .build();
    }


}
