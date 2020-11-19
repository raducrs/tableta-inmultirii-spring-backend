package ro.tabletainmultirii.api.schools.domain.vo;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class Source {
    private String name;
    private String url;
    private LocalDateTime accessed;
}
