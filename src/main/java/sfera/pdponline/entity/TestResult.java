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

public class TestResult extends AbsEntity {

    @ManyToOne
    private Tests test;

    private Long correctCount;

    private Long incorrectCount;

    private double score;


}
