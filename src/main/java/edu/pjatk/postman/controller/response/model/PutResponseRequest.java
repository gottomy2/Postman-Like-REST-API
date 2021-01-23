package edu.pjatk.postman.controller.response.model;


import lombok.*;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple Put Request model for ResponseController
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PutResponseRequest {
    private Long id;
    private Long requestId;
    private String response;
}
