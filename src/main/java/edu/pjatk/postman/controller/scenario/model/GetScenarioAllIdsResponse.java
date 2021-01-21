package edu.pjatk.postman.controller.scenario.model;

import lombok.*;

import java.util.List;

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
