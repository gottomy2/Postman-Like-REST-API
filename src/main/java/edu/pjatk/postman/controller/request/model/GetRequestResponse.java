package edu.pjatk.postman.controller.request.model;

import lombok.*;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple Get Response model for RequestController
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetRequestResponse {
    private Long id;
    private Long userId;
    private String url,header,type;
}
