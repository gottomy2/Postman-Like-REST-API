package edu.pjatk.postman.db.controller.response.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseResponse {
    Long id;
    private Long requestId;
    private String response;
}
