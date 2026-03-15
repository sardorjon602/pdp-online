package sfera.pdponline.entity;

import jakarta.persistence.Entity;
import lombok.*;
import sfera.pdponline.entity.template.AbsEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class TestResult extends AbsEntity {
    private Long testId;
    private Long correctCount;
    private Long incorrectCount;
    private double score;


}
