package edu.pjatk.postman.controller.param.model;

import lombok.*;

import java.util.List;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple Get Response model for ParamController
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetParamsResponses {
    private List<Long> paramIdList;
}
