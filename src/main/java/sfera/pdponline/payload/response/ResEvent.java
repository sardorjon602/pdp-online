package sfera.pdponline.payload.response;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResEvent {
    private Long id;
    private String title;
    private Date date;
    private String file;
}