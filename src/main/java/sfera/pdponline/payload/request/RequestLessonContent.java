package sfera.pdponline.payload.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestLessonContent {

    private String title;

    private Long lessonId;

    private String file;

    private Long lessonCount;
}