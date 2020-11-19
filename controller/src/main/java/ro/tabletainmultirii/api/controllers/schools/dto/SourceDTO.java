package ro.tabletainmultirii.api.controllers.schools.dto;

import lombok.*;
import ro.tabletainmultirii.api.controllers.schools.dto.serializers.LocalDateTimeSerializer;
import ro.tabletainmultirii.api.schools.domain.vo.Source;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class SourceDTO {
    private String name;
    private String url;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime accessed;

    static public SourceDTO from(Source that){
        var obj = new SourceDTO();
        obj.name = that.getName();
        obj.url = that.getUrl();
        obj.accessed = that.getAccessed();
        return obj;
    }
}
