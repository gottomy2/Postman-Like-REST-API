package edu.pjatk.postman.db.controller.request.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private Long userId;
    private String url;
    private String params;
}
