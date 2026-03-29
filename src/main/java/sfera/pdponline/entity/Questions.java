package sfera.pdponline.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;
import sfera.pdponline.entity.template.AbsEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Questions extends AbsEntity {

    @ManyToOne
    private Tests test;

    private String text;

    private String file;


}
