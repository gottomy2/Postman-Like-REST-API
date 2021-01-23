package edu.pjatk.postman.controller.response.model;

import lombok.*;

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
public class GetResponseResponse {
    Long id;
    private Long requestId;
    private String response;
}
