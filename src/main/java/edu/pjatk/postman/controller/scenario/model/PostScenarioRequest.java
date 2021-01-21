package edu.pjatk.postman.controller.scenario.model;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostScenarioRequest {
    private Long id;
    private Long userId;
    private String requestIds,name,description;
}
