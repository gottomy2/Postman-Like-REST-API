package edu.pjatk.postman.controller.request.model;

import lombok.*;

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
    private String url;
}