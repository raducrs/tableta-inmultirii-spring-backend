package ro.tabletainmultirii.api.repository.test.stats;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ro.tabletainmultirii.api.stats.domain.aggregates.Gadgets;
import ro.tabletainmultirii.api.stats.domain.repository.GadgetsRepository;
import ro.tabletainmultirii.api.stats.domain.vo.GadgetInfo;



@Service
@Profile({"dev", "default"})
public class GadgetsRepositoryImpl implements GadgetsRepository {


    @Override
    public Gadgets getCurrent() {
        return Gadgets.builder()
                .laptops(GadgetInfo.builder()
                        .count(1)
                        .build())
                .tablets(GadgetInfo.builder()
                        .count(2)
                        .build())
                .phones(GadgetInfo.builder()
                        .count(8)
                        .build())
                .build();
    }
}
