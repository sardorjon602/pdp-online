package sfera.pdponline.payload.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RequestOption {

    private Boolean correct;

    private Long questionId;

}
