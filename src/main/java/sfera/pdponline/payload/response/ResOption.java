package sfera.pdponline.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ResOption {
    private Long id;

    private Boolean correct;

    private String questionName;

}
