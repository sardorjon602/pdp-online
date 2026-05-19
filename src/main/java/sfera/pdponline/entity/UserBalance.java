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

public class UserBalance extends AbsEntity {

    @OneToOne
    private Users user;

    private double balance;

    private double realBalance;

    private double noRealBalance;

// Suhrob ->Certificate , events, TestResult
// Sardor -> payment, option, giveHomework

}
