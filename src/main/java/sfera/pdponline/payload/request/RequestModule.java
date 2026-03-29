package sfera.pdponline.payload.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestModule {

    private String title;

    private Long courseId;
}