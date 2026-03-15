package sfera.pdponline.payload.response;

import lombok.*;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResLesson {
    private Long id;
    private String title;
    private String moduleName;
    private boolean free;
    private Time durationHour;
    private String description;
}