package edu.pjatk.postman.controller.header.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetHeaderResponse {
    private Long id;
    private Long requestId;
    private String name;
    private String value;
}
