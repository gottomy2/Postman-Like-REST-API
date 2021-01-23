package edu.pjatk.postman.controller.scenario.model;

import lombok.*;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple Put Request model for ScenarioController
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PutScenarioRequest {
    private Long id;
    private Long userId;
    private String requestIds,name,description;
}
