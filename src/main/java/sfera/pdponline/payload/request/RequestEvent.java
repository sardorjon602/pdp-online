package sfera.pdponline.payload.request;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestEvent {
    private String title;
    private Date date;
    private String file;
}