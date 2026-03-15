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

public class Certificate extends AbsEntity {
    private Long userId;
    private String file;


}
