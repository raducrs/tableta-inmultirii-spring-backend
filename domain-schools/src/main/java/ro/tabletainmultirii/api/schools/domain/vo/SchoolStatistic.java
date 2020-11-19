package ro.tabletainmultirii.api.schools.domain.vo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class SchoolStatistic {
    private String name;
    private Integer count;
}
