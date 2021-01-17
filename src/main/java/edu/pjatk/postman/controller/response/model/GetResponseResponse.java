package edu.pjatk.postman.controller.response.model;

import lombok.*;

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
