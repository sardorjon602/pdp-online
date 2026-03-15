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

public class Courses extends AbsEntity {
    private String title;
    private double price;
    private String promoCod;
    private double discountPrice;
    private String level;
    private boolean active;
    private String description;



}
