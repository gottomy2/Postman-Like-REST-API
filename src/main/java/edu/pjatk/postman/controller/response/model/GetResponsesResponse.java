package edu.pjatk.postman.controller.response.model;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetResponsesResponse {
    private List<Long> responseIds;
}
