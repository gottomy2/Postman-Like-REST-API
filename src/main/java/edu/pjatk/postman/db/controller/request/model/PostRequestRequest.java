package edu.pjatk.postman.db.controller.request.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestRequest {
    private Long id;
    private Long userId;
    private String url;
}
