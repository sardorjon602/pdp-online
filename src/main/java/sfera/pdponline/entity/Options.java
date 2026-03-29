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

public class Options extends AbsEntity {

    @ManyToOne
    private Questions question;

    private boolean correct;


}
