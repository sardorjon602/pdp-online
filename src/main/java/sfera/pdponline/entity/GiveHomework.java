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

public class GiveHomework extends AbsEntity {

    private String feedback;

    @ManyToOne
    private Homework homework;

    @ManyToOne
    private Users user;

    private String file;

    private boolean status;

    private Long score;

    private Date date;




}
