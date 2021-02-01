package edu.pjatk.postman.controller.param.model;

import edu.pjatk.postman.repository.model.Param;
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
public class GetParamsByRequestIdResponse {
    List<Param> paramList;
}
