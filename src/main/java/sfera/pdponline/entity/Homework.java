package sfera.pdponline.entity;

import jakarta.persistence.Entity;
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
    private Long lessonContentId;
    private String title;
    private String description;
    private Date deadline;
    private String file;

}
