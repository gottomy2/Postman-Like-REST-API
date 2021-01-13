package edu.pjatk.postman.db.controller.Request.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PutRequest {
    private Long userId;
    private String url;
    private String params;
}
