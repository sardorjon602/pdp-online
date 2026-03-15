package sfera.pdponline.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import sfera.pdponline.entity.template.AbsEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class LessonContent extends AbsEntity {
    private String title;
    @ManyToOne
    private Lesson lesson;
    private String file;
    private Long lessonCount;

}
