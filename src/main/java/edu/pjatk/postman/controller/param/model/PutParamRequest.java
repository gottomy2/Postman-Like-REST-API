package edu.pjatk.postman.controller.param.model;

import lombok.*;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple Put Request model for ParamController
 */

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
