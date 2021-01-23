package edu.pjatk.postman.controller.scenario.model;

import lombok.*;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple template for sending POST request to ScenarioController
 */
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostScenarioRequest {
    private Long userId;
    private String requestIds,name,description;
}
