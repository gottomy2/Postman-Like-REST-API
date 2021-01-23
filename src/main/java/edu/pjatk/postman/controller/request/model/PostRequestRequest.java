package edu.pjatk.postman.controller.request.model;

import lombok.*;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple template for sending POST request to RequestController
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestRequest {
    private Long userId;
    private String url;
}
