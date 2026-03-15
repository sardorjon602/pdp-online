package sfera.pdponline.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResModule {
    private Long id;
    private String title;
    private String courseName;
}