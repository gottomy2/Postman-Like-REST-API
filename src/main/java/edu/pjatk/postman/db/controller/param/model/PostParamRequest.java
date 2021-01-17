package edu.pjatk.postman.db.controller.param.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostParamRequest {
    private Long id;
    private Long requestId;
    private String name,value;
}
