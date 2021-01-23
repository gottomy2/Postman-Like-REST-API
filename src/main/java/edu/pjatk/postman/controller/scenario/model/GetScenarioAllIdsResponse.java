package edu.pjatk.postman.controller.scenario.model;

import lombok.*;

import java.util.List;

/**
 * @author Igor Motowidło (gottomy2)
 * Simple Get Response model for ScenarioController
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetScenarioAllIdsResponse {
    List<Long> idList;
}
