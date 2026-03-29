package sfera.pdponline.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import sfera.pdponline.entity.template.AbsEntity;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Lesson extends AbsEntity {
    private String title;

    @ManyToOne
    private Modules module;

    private boolean free;

    private Time durationHour;

    private String description;


}
