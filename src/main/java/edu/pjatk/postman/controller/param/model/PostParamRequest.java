package edu.pjatk.postman.controller.param.model;

import lombok.*;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple Post Request model for ParamController
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostParamRequest {
    private Long requestId;
    private String name,value;
}
