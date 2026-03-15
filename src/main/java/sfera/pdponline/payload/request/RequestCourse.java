package sfera.pdponline.payload.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestCourse {
    private String title;
    private double price;
    private String promoCod;
    private double discountPrice;
    private String level;
    private boolean active;
    private String description;


}