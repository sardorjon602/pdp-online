package sfera.pdponline.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResLessonContent {

    private Long id;

    private String title;

    private String lessonName;

    private String file;

    private Long lessonCount;
}