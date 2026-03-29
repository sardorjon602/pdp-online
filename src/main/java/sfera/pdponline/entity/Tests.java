package sfera.pdponline.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import sfera.pdponline.entity.template.AbsEntity;

import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Tests extends AbsEntity {

    @ManyToOne
    private Users user;

    @ManyToOne
    private Modules module;

    private Time timeLimit;

    private Long questionCount;



}
