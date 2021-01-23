package edu.pjatk.postman.controller.scenario.model;

import edu.pjatk.postman.repository.model.Scenario;
import lombok.*;
import java.util.Optional;

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
public class GetScenarioByUserIdResponse {
    Optional<Scenario> scenarioList;
}
