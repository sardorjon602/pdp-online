package sfera.pdponline.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;
import sfera.pdponline.entity.template.AbsEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Payment extends AbsEntity {

    @ManyToOne
    private Users user;

    @OneToOne
    private Courses course;

    private String voucher;

    private String type;

    private boolean status;


}
