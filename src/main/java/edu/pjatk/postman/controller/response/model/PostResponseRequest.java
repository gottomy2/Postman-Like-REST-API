package edu.pjatk.postman.controller.response.model;

import lombok.*;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple Post Request model for ResponseController
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseRequest {
    private Long requestId;
    private String response;
}
