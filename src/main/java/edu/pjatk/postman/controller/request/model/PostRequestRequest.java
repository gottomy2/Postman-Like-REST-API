package edu.pjatk.postman.controller.request.model;

import lombok.*;


/**
 * @author Igor Motowidło (gottomy2)
 * Simple Post Request model for RequestController
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
    private String host;
    private String url;
    private String type;
}
