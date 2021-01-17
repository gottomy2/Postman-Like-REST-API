package edu.pjatk.postman.controller.param.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetParamResponse {
    private Long id;
    private Long requestId;
    private String name,value;
}
