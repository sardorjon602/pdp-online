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

public class Events extends AbsEntity {

    private String title;

    private Date date;

    private String file;




}
