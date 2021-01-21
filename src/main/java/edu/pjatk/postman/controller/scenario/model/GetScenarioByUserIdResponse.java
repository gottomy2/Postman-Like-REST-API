package edu.pjatk.postman.controller.scenario.model;

import edu.pjatk.postman.repository.model.Scenario;
import lombok.*;
import java.util.Optional;

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
