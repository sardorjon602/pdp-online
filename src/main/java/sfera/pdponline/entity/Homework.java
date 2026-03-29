package sfera.pdponline.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import sfera.pdponline.entity.template.AbsEntity;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Homework extends AbsEntity {

    @ManyToOne
    private LessonContent lessonContent;

    private String title;

    private String description;

    private Date deadline;

    private String file;

}
