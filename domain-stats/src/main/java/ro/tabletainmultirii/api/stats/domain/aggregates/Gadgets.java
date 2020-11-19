package ro.tabletainmultirii.api.stats.domain.aggregates;

import lombok.*;
import ro.tabletainmultirii.api.stats.domain.vo.GadgetInfo;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class Gadgets {
    private GadgetInfo laptops;
    private GadgetInfo tablets;
    private GadgetInfo phones;
}
