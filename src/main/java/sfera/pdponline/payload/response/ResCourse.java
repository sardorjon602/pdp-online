package sfera.pdponline.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResCourse {

    private Long id;

    private String title;

    private double price;

    private String promoCod;

    private double discountPrice;

    private String level;

    private boolean active;

    private String description;
}