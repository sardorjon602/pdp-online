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

public class GiveHomework extends AbsEntity {
    private String feedback;
    private Long homeworkId;
    private Long userId;
    private String file;
    private boolean status;
    private Long score;
    private Date date;



}
