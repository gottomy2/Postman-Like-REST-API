package edu.pjatk.postman.controller.response.model;

import lombok.*;
import java.util.List;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple Get Response model for ResponseController
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetResponsesResponse {
    private List<Long> responseIds;
}
