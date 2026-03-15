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

public class Payment extends AbsEntity {
    private Long userId;
    private Long courseId;
    private String voucher;
    private String type;
    private boolean status;


}
