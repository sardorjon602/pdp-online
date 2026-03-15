package sfera.pdponline.entity;

import jakarta.persistence.Entity;
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
    private Long userId;
    private Long moduleId;
    private Time timeLimit;
    private Long questionCount;



}
