package edu.pjatk.postman.controller.request.model;

import lombok.*;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple template for Put Request on RequestControler
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PutRequestRequest {
    private Long id;
    private Long userId;
    private String url;
}
