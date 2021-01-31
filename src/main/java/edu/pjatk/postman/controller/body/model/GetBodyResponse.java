package edu.pjatk.postman.controller.body.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetBodyResponse {
    private Long id;
    private Long requestId;
    private String name;
    private String value;
}
