package edu.pjatk.postman.controller.response.model;

import edu.pjatk.postman.repository.model.Response;
import lombok.*;
import java.util.Optional;

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
public class GetResponseByRequestId {
    private Optional<Response> responseList;
}
