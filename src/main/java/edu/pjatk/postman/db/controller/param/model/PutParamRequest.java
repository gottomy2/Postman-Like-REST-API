package edu.pjatk.postman.db.controller.param.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PutParamRequest {
    private Long id;
    private Long requestId;
    private String name,value;
}
