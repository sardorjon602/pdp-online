package sfera.pdponline.payload.request;

import lombok.*;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestLesson {
    private String title;
    private Long moduleId;
    private boolean free;
    private Time durationHour;
    private String description;
}